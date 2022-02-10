package com.semicolon.domain.repository

import com.semicolon.domain.entity.exercise.DailyExerciseEntity
import com.semicolon.domain.entity.exercise.ExerciseAnalysisResultEntity
import com.semicolon.domain.entity.exercise.ExerciseRecordEntity
import com.semicolon.domain.param.exercise.FinishMeasureExerciseParam
import com.semicolon.domain.param.exercise.StartMeasureExerciseParam
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {

    suspend fun fetchDailyExerciseRecord(): Flow<DailyExerciseEntity>

    suspend fun startMeasureExercise(startMeasureExerciseParam: StartMeasureExerciseParam)

    suspend fun finishMeasureExercise(finishMeasureExerciseParam: FinishMeasureExerciseParam)

    suspend fun isMeasuring(): Boolean

    suspend fun startRecordExercise()

    suspend fun fetchExerciseRecordList(): Flow<List<ExerciseRecordEntity>>

    suspend fun fetchExerciseAnalysisResult(): Flow<ExerciseAnalysisResultEntity>
}