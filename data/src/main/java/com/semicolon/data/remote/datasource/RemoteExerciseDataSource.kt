package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.request.exercise.FinishMeasureExerciseRequest
import com.semicolon.data.remote.request.exercise.SaveDailyExerciseRequest
import com.semicolon.data.remote.request.exercise.SendLocationRecordsRequest
import com.semicolon.data.remote.request.exercise.StartMeasureExerciseRequest
import com.semicolon.data.remote.response.exercise.ExerciseAnalysisResultResponse
import com.semicolon.data.remote.response.exercise.ExerciseIdResponse
import com.semicolon.data.remote.response.exercise.ExerciseRecordListResponse
import com.semicolon.data.remote.response.exercise.ExercisingUserListResponse

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
        saveDailyExerciseRequest: SaveDailyExerciseRequest
    )

    suspend fun fetchExerciseRecordList(): ExerciseRecordListResponse

    suspend fun fetchExerciseAnalysisResult(): ExerciseAnalysisResultResponse

    suspend fun fetchExercisingUserList(): ExercisingUserListResponse
}