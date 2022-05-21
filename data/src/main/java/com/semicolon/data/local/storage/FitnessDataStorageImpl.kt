package com.semicolon.data.local.storage

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import com.google.android.gms.fitness.request.DataReadRequest
import com.semicolon.data.local.entity.exercise.LocationRecordEntity
import com.semicolon.data.local.entity.exercise.WalkRecordEntity
import com.semicolon.data.local.param.PeriodParam
import com.semicolon.domain.entity.exercise.DailyExerciseEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FitnessDataStorageImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : FitnessDataStorage {

    private val fitnessOptions: FitnessOptions by lazy {
        FitnessOptions.builder()
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA)
            .addDataType(DataType.AGGREGATE_MOVE_MINUTES)
            .addDataType(DataType.AGGREGATE_DISTANCE_DELTA)
            .addDataType(DataType.AGGREGATE_CALORIES_EXPENDED)
            .addDataType(DataType.TYPE_LOCATION_SAMPLE)
            .build()
    }

    override suspend fun fetchDailyExerciseRecord(): Flow<DailyExerciseEntity> =
        callbackFlow {
            repeat(Int.MAX_VALUE) {
                val startTime =
                    LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toEpochSecond()
                val endTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()
                val data = Fitness.getHistoryClient(context, getGoogleAccount())
                    .readData(
                        DataReadRequest.Builder()
                            .aggregate(DataType.AGGREGATE_STEP_COUNT_DELTA)
                            .aggregate(DataType.AGGREGATE_MOVE_MINUTES)
                            .aggregate(DataType.AGGREGATE_DISTANCE_DELTA)
                            .aggregate(DataType.AGGREGATE_CALORIES_EXPENDED)
                            .setTimeRange(
                                startTime,
                                endTime,
                                TimeUnit.SECONDS
                            )
                            .bucketByTime(1, TimeUnit.DAYS)
                            .build()
                    )
                var steps: Int
                var minutes: Int
                var distance: Int
                var calories: Float
                data.addOnSuccessListener {
                    steps = it.buckets.firstOrNull()
                        ?.getDataSet(DataType.AGGREGATE_STEP_COUNT_DELTA)?.dataPoints?.firstOrNull()
                        ?.getValue(Field.FIELD_STEPS)?.asInt() ?: 0
                    minutes = it.buckets.firstOrNull()
                        ?.getDataSet(DataType.AGGREGATE_MOVE_MINUTES)?.dataPoints?.firstOrNull()
                        ?.getValue(Field.FIELD_DURATION)?.asInt() ?: 0
                    distance = it.buckets.firstOrNull()
                        ?.getDataSet(DataType.AGGREGATE_DISTANCE_DELTA)?.dataPoints?.firstOrNull()
                        ?.getValue(Field.FIELD_DISTANCE)?.asFloat()?.toInt() ?: 0
                    calories = it.buckets.firstOrNull()
                        ?.getDataSet(DataType.AGGREGATE_CALORIES_EXPENDED)?.dataPoints?.firstOrNull()
                        ?.getValue(Field.FIELD_CALORIES)?.asFloat() ?: 0f

                    trySend(
                        DailyExerciseEntity(
                            steps,
                            minutes.toLong() * 60000,
                            distance,
                            calories
                        )
                    )
                }
                delay(1000)
            }
            awaitClose {}
        }

    override suspend fun fetchLocationRecord(periodParam: PeriodParam): List<LocationRecordEntity> {
        val data = Fitness.getHistoryClient(context, getGoogleAccount())
            .readData(
                DataReadRequest.Builder()
                    .aggregate(DataType.TYPE_LOCATION_SAMPLE)
                    .setTimeRange(
                        periodParam.startTimeAsSecond,
                        periodParam.endTimeAsSecond,
                        TimeUnit.SECONDS
                    )
                    .bucketByTime(5, TimeUnit.MINUTES)
                    .build()
            )
        return suspendCoroutine {
            data.addOnSuccessListener { response ->
                val result = response.buckets
                    .mapNotNull { bucket -> bucket.getDataSet(DataType.TYPE_LOCATION_SAMPLE)?.dataPoints?.firstOrNull() }
                    .map { dataPoint ->
                        val latitude =
                            dataPoint.getValue(Field.FIELD_LATITUDE).asString().toDouble()
                        val longitude =
                            dataPoint.getValue(Field.FIELD_LONGITUDE).asString().toDouble()
                        LocationRecordEntity(latitude = latitude, longitude = longitude)
                    }
                it.resume(result)
            }
        }
    }

    override suspend fun fetchWalkRecord(periodParam: PeriodParam): WalkRecordEntity {
        val data = Fitness.getHistoryClient(context, getGoogleAccount())
            .readData(
                DataReadRequest.Builder()
                    .aggregate(DataType.AGGREGATE_STEP_COUNT_DELTA)
                    .aggregate(DataType.AGGREGATE_DISTANCE_DELTA)
                    .setTimeRange(
                        periodParam.startTimeAsSecond,
                        periodParam.endTimeAsSecond,
                        TimeUnit.SECONDS
                    )
                    .bucketByTime(1, TimeUnit.DAYS)
                    .build()
            )
        return suspendCoroutine {
            data.addOnSuccessListener { response ->
                val steps = response.buckets.firstOrNull()
                    ?.getDataSet(DataType.AGGREGATE_STEP_COUNT_DELTA)?.dataPoints?.firstOrNull()
                    ?.getValue(Field.FIELD_STEPS)?.asInt() ?: 0
                val distance = response.buckets.firstOrNull()
                    ?.getDataSet(DataType.AGGREGATE_DISTANCE_DELTA)?.dataPoints?.firstOrNull()
                    ?.getValue(Field.FIELD_DISTANCE)?.asFloat()?.toInt() ?: 0
                val calories = response.buckets.firstOrNull()
                    ?.getDataSet(DataType.AGGREGATE_CALORIES_EXPENDED)?.dataPoints?.firstOrNull()
                    ?.getValue(Field.FIELD_CALORIES)?.asFloat() ?: 0f

                it.resume(
                    WalkRecordEntity(
                        traveledDistanceAsMeter = distance,
                        walkCount = steps,
                        burnedKilocalories = calories / 1000
                    )
                )
            }
        }
    }

    override suspend fun startRecordExercise(
        onSuccess: (DataType) -> Unit,
        onFailure: (DataType) -> Unit
    ) {
        val stepCount = DataType.AGGREGATE_STEP_COUNT_DELTA
        val moveMinute = DataType.AGGREGATE_MOVE_MINUTES
        val distance = DataType.AGGREGATE_DISTANCE_DELTA
        val calories = DataType.AGGREGATE_CALORIES_EXPENDED
        val location = DataType.TYPE_LOCATION_SAMPLE

        Fitness.getRecordingClient(context, getGoogleAccount()).subscribe(stepCount)
            .addOnSuccessListener { onSuccess(stepCount) }
            .addOnFailureListener { onFailure(stepCount) }

        Fitness.getRecordingClient(context, getGoogleAccount()).subscribe(moveMinute)
            .addOnSuccessListener { onSuccess(moveMinute) }
            .addOnFailureListener { onFailure(moveMinute) }

        Fitness.getRecordingClient(context, getGoogleAccount()).subscribe(distance)
            .addOnSuccessListener { onSuccess(distance) }
            .addOnFailureListener { onFailure(distance) }

        Fitness.getRecordingClient(context, getGoogleAccount()).subscribe(calories)
            .addOnSuccessListener { onSuccess(calories) }
            .addOnFailureListener { onFailure(calories) }

        Fitness.getRecordingClient(context, getGoogleAccount()).subscribe(location)
            .addOnSuccessListener { onSuccess(location) }
            .addOnFailureListener { onFailure(location) }
    }


    private fun getGoogleAccount() =
        GoogleSignIn.getAccountForExtension(context, fitnessOptions)
}