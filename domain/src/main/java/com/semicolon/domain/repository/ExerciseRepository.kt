package com.semicolon.domain.repository

import com.semicolon.domain.entity.exercise.DailyExerciseEntity
import com.semicolon.domain.param.exercise.FinishExerciseMeasureParam
import com.semicolon.domain.param.exercise.StartExerciseMeasureParam
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {

    suspend fun fetchDailyExerciseRecord(): Flow<DailyExerciseEntity>

    suspend fun startMeasureExercise(startExerciseMeasureParam: StartExerciseMeasureParam)

    suspend fun finishMeasureExercise(finishExerciseMeasureParam: FinishExerciseMeasureParam)

    suspend fun isMeasuring(): Boolean

    suspend fun startRecordExercise()
}