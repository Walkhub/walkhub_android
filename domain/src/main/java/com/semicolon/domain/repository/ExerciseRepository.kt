package com.semicolon.domain.repository

import com.semicolon.domain.entity.exercise.*
import com.semicolon.domain.enums.MeasuringState
import com.semicolon.domain.param.exercise.FinishMeasureExerciseParam
import com.semicolon.domain.param.exercise.StartMeasureExerciseParam
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {

    suspend fun fetchDailyExerciseRecord(): Flow<DailyExerciseEntity>

    suspend fun startMeasureExercise(startMeasureExerciseParam: StartMeasureExerciseParam)

    suspend fun pauseMeasureExercise()

    suspend fun resumeMeasureExercise()

    suspend fun finishMeasureExercise(finishMeasureExerciseParam: FinishMeasureExerciseParam)

    suspend fun isMeasuring(): MeasuringState

    suspend fun startRecordExercise()

    suspend fun fetchExerciseRecordList(): Flow<List<ExerciseRecordEntity>>

    suspend fun fetchExerciseAnalysisResult(): Flow<ExerciseAnalysisResultEntity>

    suspend fun fetchMeasuredExerciseRecord(): Flow<ExerciseEntity>

    suspend fun fetchMeasuredTime(): Flow<Long>

    suspend fun fetchCurrentSpeed(): Flow<Float>

    suspend fun fetchGoal(): GoalEntity

    suspend fun fetchExercisingUserList(): Flow<List<ExercisingUserEntity>>
}