package com.semicolon.data.local.datasource

import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import com.semicolon.data.local.entity.exercise.LocationRecordEntity
import com.semicolon.data.local.entity.exercise.WalkRecordEntity
import com.semicolon.data.local.param.PeriodParam
import com.semicolon.data.local.storage.ExerciseInfoDataStorage
import com.semicolon.data.local.storage.FitnessDataStorage
import com.semicolon.domain.entity.exercise.DailyExerciseEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import javax.inject.Inject

class LocalExerciseDataSourceImpl @Inject constructor(
    private val fitnessDataStorage: FitnessDataStorage,
    private val exerciseInfoDataStorage: ExerciseInfoDataStorage
) : LocalExerciseDataSource {

    override suspend fun fetchDailyExerciseRecordAsFlow(): Flow<DailyExerciseEntity> =
        flow {
            repeat(Int.MAX_VALUE) {
                delay(1000)
                val startTime =
                    LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toEpochSecond()
                val endTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()
                val data = fitnessDataStorage.fetchExerciseRecord(
                    PeriodParam(
                        startTimeAsMilli = startTime,
                        endTimeAsMilli = endTime
                    )
                )
                emit(
                    DailyExerciseEntity(
                        stepCount = data.result.getDataSet(DataType.AGGREGATE_STEP_COUNT_DELTA).dataPoints[0]
                            .getValue(Field.FIELD_STEPS).asInt(),
                        exerciseTimeAsMilli = data.result.getDataSet(DataType.AGGREGATE_STEP_COUNT_DELTA).dataPoints[0]
                            .getValue(Field.FIELD_DURATION).asInt().toLong() * 1000 * 60,
                        traveledDistanceAsMeter = data.result.getDataSet(DataType.AGGREGATE_STEP_COUNT_DELTA).dataPoints[0]
                            .getValue(Field.FIELD_DISTANCE).asInt(),
                        burnedKilocalories = data.result.getDataSet(DataType.AGGREGATE_STEP_COUNT_DELTA).dataPoints[0]
                            .getValue(Field.FIELD_CALORIES).asFloat()
                    )
                )
            }
        }

    override suspend fun fetchLocationRecord(periodParam: PeriodParam): List<LocationRecordEntity> {
        val data = fitnessDataStorage.fetchLocationRecord(periodParam)
        return data.result.getDataSet(DataType.TYPE_LOCATION_SAMPLE).dataPoints.map {
            val latitude = it.getValue(Field.FIELD_LATITUDE).asString().toDouble()
            val longitude = it.getValue(Field.FIELD_LONGITUDE).asString().toDouble()
            LocationRecordEntity(latitude = latitude, longitude = longitude)
        }
    }

    override suspend fun fetchWalkRecord(periodParam: PeriodParam): WalkRecordEntity {
        val data = fitnessDataStorage.fetchExerciseRecord(periodParam)
        return WalkRecordEntity(
            traveledDistanceAsMeter = data.result.getDataSet(DataType.AGGREGATE_STEP_COUNT_DELTA).dataPoints[0]
                .getValue(Field.FIELD_STEPS).asInt(),
            walkCount = data.result.getDataSet(DataType.AGGREGATE_DISTANCE_DELTA).dataPoints[0]
                .getValue(Field.FIELD_DISTANCE).asInt(),
            burnedKilocalories = data.result.getDataSet(DataType.AGGREGATE_STEP_COUNT_DELTA).dataPoints[0]
                .getValue(Field.FIELD_CALORIES).asFloat()
        )
    }

    override suspend fun fetchStartTime(): Long =
        exerciseInfoDataStorage.fetchStartTime()

    override suspend fun fetchExerciseId(): Int =
        exerciseInfoDataStorage.fetchExerciseId()

    override suspend fun isMeasuring(): Boolean =
        exerciseInfoDataStorage.isMeasuring()

    override suspend fun startMeasuring(startTimeAsMilli: Long, exerciseId: Int) {
        exerciseInfoDataStorage.run {
            setExerciseId(exerciseId)
            setStartTime(startTimeAsMilli)
            setIsMeasuring(true)
        }
    }

    override suspend fun finishMeasuring() {
        exerciseInfoDataStorage.setIsMeasuring(false)
    }
}