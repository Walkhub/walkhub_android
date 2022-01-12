package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.request.exercise.FinishMeasureExerciseRequest
import com.semicolon.data.remote.request.exercise.SaveDailyExerciseRequest
import com.semicolon.data.remote.request.exercise.SendLocationRecordsRequest
import com.semicolon.data.remote.request.exercise.StartMeasureExerciseRequest
import com.semicolon.data.remote.response.exercise.ExerciseIdResponse

interface RemoteExerciseDataSource {

    suspend fun startMeasureExercise(
        startMeasureExerciseRequest: StartMeasureExerciseRequest
    ): ExerciseIdResponse

    suspend fun finishMeasureExercise(
        exerciseId: Int,
        finishMeasureExerciseRequest: FinishMeasureExerciseRequest
    )

    suspend fun sendLocationRecords(
        exerciseId: Int,
        sendLocationRecordsRequest: SendLocationRecordsRequest
    )

    suspend fun saveDailyExercise(
        date: String,
        saveDailyExerciseRequest: SaveDailyExerciseRequest
    )
}