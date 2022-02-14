package com.semicolon.data.local.datasource

import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import com.semicolon.data.local.entity.exercise.LocationRecordEntity
import com.semicolon.data.local.entity.exercise.WalkRecordEntity
import com.semicolon.data.local.param.PeriodParam
import com.semicolon.data.local.storage.ExerciseInfoDataStorage
import com.semicolon.data.local.storage.FitnessDataStorage
import com.semicolon.domain.entity.exercise.DailyExerciseEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocalExerciseDataSourceImpl @Inject constructor(
    private val fitnessDataStorage: FitnessDataStorage,
    private val exerciseInfoDataStorage: ExerciseInfoDataStorage
) : LocalExerciseDataSource {

    override suspend fun fetchDailyExerciseRecordAsFlow(): Flow<DailyExerciseEntity> =
        callbackFlow {
            repeat(Int.MAX_VALUE) {
                val startTime =
                    LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toEpochSecond()
                val endTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()
                val data = fitnessDataStorage.fetchExerciseRecord(
                    PeriodParam(
                        startTimeAsSecond = startTime,
                        endTimeAsSecond = endTime
                    )
                )
                var steps: Int
                var minutes: Int
                var distance: Int
                var calories: Float
                data.addOnSuccessListener {
                    println(it.buckets)
                    steps = it.buckets.firstOrNull()
                        ?.getDataSet(DataType.AGGREGATE_STEP_COUNT_DELTA)!!.dataPoints.firstOrNull()
                        ?.getValue(Field.FIELD_STEPS)?.asInt() ?: 0
                    minutes = it.buckets.firstOrNull()
                        ?.getDataSet(DataType.AGGREGATE_MOVE_MINUTES)!!.dataPoints.firstOrNull()
                        ?.getValue(Field.FIELD_DURATION)?.asInt() ?: 0
                    distance = it.buckets.firstOrNull()
                        ?.getDataSet(DataType.AGGREGATE_DISTANCE_DELTA)!!.dataPoints.firstOrNull()
                        ?.getValue(Field.FIELD_DISTANCE)?.asInt() ?: 0
                    calories = it.buckets.firstOrNull()
                        ?.getDataSet(DataType.AGGREGATE_CALORIES_EXPENDED)!!.dataPoints.firstOrNull()
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
                delay(10000)
            }
            awaitClose {}
        }

    override suspend fun fetchLocationRecord(periodParam: PeriodParam): List<LocationRecordEntity> {
        val data = fitnessDataStorage.fetchLocationRecord(periodParam)
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
        val data = fitnessDataStorage.fetchExerciseRecord(periodParam)
        return suspendCoroutine {
            data.addOnSuccessListener { response ->
                val steps = response.buckets.firstOrNull()
                    ?.getDataSet(DataType.AGGREGATE_STEP_COUNT_DELTA)!!.dataPoints.firstOrNull()
                    ?.getValue(Field.FIELD_STEPS)?.asInt() ?: 0
                val distance = response.buckets.firstOrNull()
                    ?.getDataSet(DataType.AGGREGATE_DISTANCE_DELTA)!!.dataPoints.firstOrNull()
                    ?.getValue(Field.FIELD_DISTANCE)?.asInt() ?: 0
                val calories = response.buckets.firstOrNull()
                    ?.getDataSet(DataType.AGGREGATE_CALORIES_EXPENDED)!!.dataPoints.firstOrNull()
                    ?.getValue(Field.FIELD_CALORIES)?.asFloat() ?: 0f

                it.resume(
                    WalkRecordEntity(
                        traveledDistanceAsMeter = distance,
                        walkCount = steps,
                        burnedKilocalories = calories
                    )
                )
            }
        }
    }

    override suspend fun fetchStartTime(): Long =
        exerciseInfoDataStorage.fetchStartTime()

    override suspend fun fetchExerciseId(): Int =
        exerciseInfoDataStorage.fetchExerciseId()

    override suspend fun isMeasuring(): Boolean =
        exerciseInfoDataStorage.isMeasuring()

    override suspend fun startMeasuring(startTimeAsSecond: Long, exerciseId: Int) {
        exerciseInfoDataStorage.run {
            setExerciseId(exerciseId)
            setStartTime(startTimeAsSecond)
            setIsMeasuring(true)
        }
    }

    override suspend fun finishMeasuring() {
        exerciseInfoDataStorage.setIsMeasuring(false)
    }
}