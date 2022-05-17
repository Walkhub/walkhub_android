package com.semicolon.data.local.datasource

import com.semicolon.data.local.entity.exercise.LocationRecordEntity
import com.semicolon.data.local.entity.exercise.WalkRecordEntity
import com.semicolon.data.local.param.PeriodParam
import com.semicolon.domain.entity.exercise.DailyExerciseEntity
import com.semicolon.domain.entity.exercise.ExerciseEntity
import com.semicolon.domain.entity.exercise.GoalEntity
import com.semicolon.domain.enums.MeasuringState
import kotlinx.coroutines.flow.Flow

interface LocalExerciseDataSource {

    suspend fun fetchDailyExerciseRecordAsFlow(): Flow<DailyExerciseEntity>

    suspend fun fetchLocationRecord(periodParam: PeriodParam): List<LocationRecordEntity>

    suspend fun fetchWalkRecord(periodParam: PeriodParam): WalkRecordEntity

    suspend fun startRecordExercise()

    suspend fun fetchStartTime(): Long

    suspend fun fetchExerciseId(): Int

    suspend fun isMeasuring(): MeasuringState

    suspend fun startMeasuring(startTimeAsSecond: Long, exerciseId: Int)

    suspend fun pauseMeasuring(
        steps: Int,
        distanceAsMeter: Int,
        burnedKilocalories: Float,
        locationRecord: List<LocationRecordEntity>
    )

    suspend fun finishMeasuring()

    suspend fun fetchAccumulatedRecord(): WalkRecordEntity

    suspend fun fetchAccumulatedLocationRecord(): List<LocationRecordEntity>

    suspend fun fetchPausedTime(): Long

    suspend fun fetchMeasuredExerciseRecord(): Flow<ExerciseEntity>

    suspend fun fetchMeasuredTime(): Flow<Long>

    suspend fun fetchCurrentSpeed(): Flow<Float>

    suspend fun setGoal(goalEntity: GoalEntity)

    suspend fun fetchGoal(): GoalEntity

    suspend fun addLocationRecord(locationRecordEntity: LocationRecordEntity)
}