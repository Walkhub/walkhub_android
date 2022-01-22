package com.semicolon.data.local.datasource

import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import com.semicolon.data.local.entity.exercise.LocationRecordEntity
import com.semicolon.data.local.entity.exercise.WalkRecordEntity
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
    private val fitnessDataStorage: FitnessDataStorage
) : LocalExerciseDataSource {

    override suspend fun fetchDailyExerciseRecordAsFlow(): Flow<DailyExerciseEntity> =
        flow {
            repeat(Int.MAX_VALUE) {
                delay(1000)
                val startTime = LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toEpochSecond()
                val endTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()
                val data = fitnessDataStorage.fetchExerciseRecord(
                    startTimeAsMilli = startTime,
                    endTimeAsMilli = endTime
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

    override suspend fun fetchLocationRecord(
        startTimeAsMilli: Long,
        endTimeAsMilli: Long
    ): List<LocationRecordEntity> {
        val data = fitnessDataStorage.fetchLocationRecord(startTimeAsMilli, endTimeAsMilli)
        return data.result.getDataSet(DataType.TYPE_LOCATION_SAMPLE).dataPoints.map {
            val latitude = it.getValue(Field.FIELD_LATITUDE).asString().toDouble()
            val longitude = it.getValue(Field.FIELD_LONGITUDE).asString().toDouble()
            LocationRecordEntity(latitude = latitude, longitude = longitude)
        }
    }

    override suspend fun fetchWalkRecord(
        startTimeAsMilli: Long,
        endTimeAsMilli: Long
    ): WalkRecordEntity {
        val data = fitnessDataStorage.fetchExerciseRecord(startTimeAsMilli, endTimeAsMilli)
        return WalkRecordEntity(
            traveledDistanceAsMeter = data.result.getDataSet(DataType.AGGREGATE_STEP_COUNT_DELTA).dataPoints[0]
                .getValue(Field.FIELD_STEPS).asInt(),
            walkCount = data.result.getDataSet(DataType.AGGREGATE_DISTANCE_DELTA).dataPoints[0]
                .getValue(Field.FIELD_DISTANCE).asInt()
        )
    }
}