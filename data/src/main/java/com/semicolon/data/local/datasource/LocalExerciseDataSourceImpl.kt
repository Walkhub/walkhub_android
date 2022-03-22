package com.semicolon.data.local.datasource

import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import com.semicolon.data.local.dao.LocationRecordDao
import com.semicolon.data.local.entity.exercise.LocationRecordEntity
import com.semicolon.data.local.entity.exercise.WalkRecordEntity
import com.semicolon.data.local.entity.exercise.toEntity
import com.semicolon.data.local.entity.exercise.toRoomEntity
import com.semicolon.data.local.param.PeriodParam
import com.semicolon.data.local.storage.ExerciseInfoDataStorage
import com.semicolon.data.local.storage.FitnessAccumulateDataStorage
import com.semicolon.data.local.storage.FitnessDataStorage
import com.semicolon.data.local.storage.SpeedDataStorage
import com.semicolon.domain.entity.exercise.DailyExerciseEntity
import com.semicolon.domain.entity.exercise.ExerciseEntity
import com.semicolon.domain.entity.exercise.GoalEntity
import com.semicolon.domain.enums.MeasuringState
import com.semicolon.domain.exception.exercise.RecordExerciseException
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocalExerciseDataSourceImpl @Inject constructor(
    private val fitnessDataStorage: FitnessDataStorage,
    private val exerciseInfoDataStorage: ExerciseInfoDataStorage,
    private val fitnessAccumulateDataStorage: FitnessAccumulateDataStorage,
    private val speedDataStorage: SpeedDataStorage,
    private val locationRecordDao: LocationRecordDao
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
                        ?.getValue(Field.FIELD_DISTANCE)?.asFloat()?.toInt() ?: 0
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
                delay(1000)
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
                    ?.getValue(Field.FIELD_DISTANCE)?.asFloat()?.toInt() ?: 0
                val calories = response.buckets.firstOrNull()
                    ?.getDataSet(DataType.AGGREGATE_CALORIES_EXPENDED)!!.dataPoints.firstOrNull()
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

    override suspend fun startRecordExercise() {
        fitnessDataStorage.startRecordExercise(
            onSuccess = {
                println("success ${it.name}")
            },
            onFailure = {
                println("failure ${it.name}")
                throw RecordExerciseException()
            }
        )
    }

    override suspend fun fetchStartTime(): Long =
        exerciseInfoDataStorage.fetchStartTime()

    override suspend fun fetchExerciseId(): Int =
        exerciseInfoDataStorage.fetchExerciseId()

    override suspend fun isMeasuring(): MeasuringState =
        exerciseInfoDataStorage.isMeasuring()

    override suspend fun startMeasuring(startTimeAsSecond: Long, exerciseId: Int) {
        if (isMeasuring() == MeasuringState.PAUSED) {
            val pausedTime = startTimeAsSecond - exerciseInfoDataStorage.fetchPausedTime()
            fitnessAccumulateDataStorage.addPausedTime(pausedTime)
        } else if (isMeasuring() == MeasuringState.FINISHED) {
            exerciseInfoDataStorage.setFirstStartTime(startTimeAsSecond)
        }
        exerciseInfoDataStorage.run {
            setExerciseId(exerciseId)
            setStartTime(startTimeAsSecond)
            setIsMeasuring(MeasuringState.ONGOING)
        }
    }

    override suspend fun pauseMeasuring(
        steps: Int,
        distanceAsMeter: Int,
        burnedKilocalories: Float,
        locationRecord: List<LocationRecordEntity>
    ) {
        val curTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()
        exerciseInfoDataStorage.setPausedTime(curTime)
        exerciseInfoDataStorage.setIsMeasuring(MeasuringState.PAUSED)
        locationRecordDao.addLocationRecords(locationRecord.map { it.toRoomEntity() })
        fitnessAccumulateDataStorage.accumulate(
            WalkRecordEntity(
                walkCount = steps,
                traveledDistanceAsMeter = distanceAsMeter,
                burnedKilocalories = burnedKilocalories
            )
        )
    }

    override suspend fun finishMeasuring() {
        exerciseInfoDataStorage.setIsMeasuring(MeasuringState.FINISHED)
        fitnessAccumulateDataStorage.clearAccumulatedData()
        fitnessAccumulateDataStorage.clearPausedTime()
        locationRecordDao.clearLocationRecords()
    }

    override suspend fun fetchAccumulatedRecord(): WalkRecordEntity =
        fitnessAccumulateDataStorage.fetchAccumulatedData()

    override suspend fun fetchAccumulatedLocationRecord(): List<LocationRecordEntity> =
        locationRecordDao.fetchLocationRecords().map { it.toEntity() }

    override suspend fun fetchPausedTime(): Long =
        fitnessAccumulateDataStorage.fetchPausedTime()

    override suspend fun fetchMeasuredExerciseRecord(): Flow<ExerciseEntity> =
        callbackFlow {
            val accumulatedHistory = fetchAccumulatedRecord()
            repeat(Int.MAX_VALUE) {
                delay(1000)
                val startTime = fetchStartTime()
                val endTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()
                val data = fitnessDataStorage.fetchExerciseRecord(
                    PeriodParam(
                        startTimeAsSecond = startTime,
                        endTimeAsSecond = endTime
                    )
                )
                var steps: Int
                var distance: Int
                var calories: Float
                data.addOnSuccessListener {
                    println(it.buckets)
                    steps = it.buckets.firstOrNull()
                        ?.getDataSet(DataType.AGGREGATE_STEP_COUNT_DELTA)!!.dataPoints.firstOrNull()
                        ?.getValue(Field.FIELD_STEPS)?.asInt() ?: 0
                    distance = it.buckets.firstOrNull()
                        ?.getDataSet(DataType.AGGREGATE_DISTANCE_DELTA)!!.dataPoints.firstOrNull()
                        ?.getValue(Field.FIELD_DISTANCE)?.asFloat()?.toInt() ?: 0
                    calories = it.buckets.firstOrNull()
                        ?.getDataSet(DataType.AGGREGATE_CALORIES_EXPENDED)!!.dataPoints.firstOrNull()
                        ?.getValue(Field.FIELD_CALORIES)?.asFloat() ?: 0f

                    trySend(
                        ExerciseEntity(
                            steps + accumulatedHistory.walkCount,
                            distance + accumulatedHistory.traveledDistanceAsMeter,
                            (calories / 1000) + accumulatedHistory.burnedKilocalories
                        )
                    )
                }
                delay(2000)
            }
            awaitClose {}
        }

    override suspend fun fetchMeasuredTime(): Flow<Long> {
        val firstStartTime = exerciseInfoDataStorage.fetchFirstStartTime()
        return flow {
            repeat(Int.MAX_VALUE) {
                delay(10000)
                val curTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()
                val measuredTime = curTime - firstStartTime - fetchPausedTime()
                emit(measuredTime)
            }
        }
    }

    override suspend fun fetchCurrentSpeed(): Flow<Float> =
        speedDataStorage.fetchCurrentSpeed()

    override suspend fun setGoal(goalEntity: GoalEntity) =
        exerciseInfoDataStorage.setGoal(goalEntity)

    override suspend fun fetchGoal(): GoalEntity =
        exerciseInfoDataStorage.fetchGoal()

}