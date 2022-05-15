package com.semicolon.data.local.datasource

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
import com.semicolon.domain.exception.RecordExerciseException
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import javax.inject.Inject

class LocalExerciseDataSourceImpl @Inject constructor(
    private val fitnessDataStorage: FitnessDataStorage,
    private val exerciseInfoDataStorage: ExerciseInfoDataStorage,
    private val fitnessAccumulateDataStorage: FitnessAccumulateDataStorage,
    private val speedDataStorage: SpeedDataStorage,
    private val locationRecordDao: LocationRecordDao
) : LocalExerciseDataSource {

    override suspend fun fetchDailyExerciseRecordAsFlow(): Flow<DailyExerciseEntity> =
        fitnessDataStorage.fetchDailyExerciseRecord()

    override suspend fun fetchLocationRecord(periodParam: PeriodParam): List<LocationRecordEntity> =
        fitnessDataStorage.fetchLocationRecord(periodParam)

    override suspend fun fetchWalkRecord(periodParam: PeriodParam): WalkRecordEntity =
        fitnessDataStorage.fetchWalkRecord(periodParam)

    override suspend fun startRecordExercise() {
        fitnessDataStorage.startRecordExercise(
            onSuccess = {},
            onFailure = { throw RecordExerciseException() }
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
        fitnessAccumulateDataStorage.accumulate(
            WalkRecordEntity(
                walkCount = steps,
                traveledDistanceAsMeter = distanceAsMeter,
                burnedKilocalories = burnedKilocalories
            )
        )
        withContext(Dispatchers.IO) {
            locationRecordDao.addLocationRecords(locationRecord.map { it.toRoomEntity() })
        }
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
                val data = fitnessDataStorage.fetchWalkRecord(
                    PeriodParam(startTimeAsSecond = startTime, endTimeAsSecond = endTime)
                )
                trySend(
                    ExerciseEntity(
                        data.walkCount + accumulatedHistory.walkCount,
                        data.traveledDistanceAsMeter + accumulatedHistory.traveledDistanceAsMeter,
                        data.burnedKilocalories + accumulatedHistory.burnedKilocalories
                    )
                )

                delay(2000)
            }
            awaitClose {}
        }

    override suspend fun fetchMeasuredTime(): Flow<Long> {
        val firstStartTime = exerciseInfoDataStorage.fetchFirstStartTime()
        return flow {
            repeat(Int.MAX_VALUE) {
                val curTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()
                val measuredTime = curTime - firstStartTime - fetchPausedTime()
                emit(measuredTime)
                delay(10000)
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