package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.ExerciseApi
import com.semicolon.data.remote.request.exercise.FinishMeasureExerciseRequest
import com.semicolon.data.remote.request.exercise.SaveDailyExerciseRequest
import com.semicolon.data.remote.request.exercise.SendLocationRecordsRequest
import com.semicolon.data.remote.request.exercise.StartMeasureExerciseRequest
import com.semicolon.data.remote.response.exercise.ExerciseAnalysisResultResponse
import com.semicolon.data.remote.response.exercise.ExerciseIdResponse
import com.semicolon.data.remote.response.exercise.ExerciseRecordListResponse
import com.semicolon.data.util.HttpHandler
import javax.inject.Inject

class RemoteExerciseDataSourceImpl @Inject constructor(
    private val exerciseApi: ExerciseApi
) : RemoteExerciseDataSource {

    override suspend fun startMeasureExercise(
        startMeasureExerciseRequest: StartMeasureExerciseRequest
    ): ExerciseIdResponse = HttpHandler<ExerciseIdResponse>()
        .httpRequest { exerciseApi.startMeasureExercise(startMeasureExerciseRequest) }
        .sendRequest()

    override suspend fun finishMeasureExercise(
        exerciseId: Int,
        finishMeasureExerciseRequest: FinishMeasureExerciseRequest
    ) = HttpHandler<Unit>()
        .httpRequest { exerciseApi.finishMeasureExercise(exerciseId, finishMeasureExerciseRequest) }
        .sendRequest()

    override suspend fun sendLocationRecords(
        exerciseId: Int,
        sendLocationRecordsRequest: SendLocationRecordsRequest
    ) = HttpHandler<Unit>()
        .httpRequest { exerciseApi.sendLocationRecords(exerciseId, sendLocationRecordsRequest) }
        .sendRequest()

    override suspend fun saveDailyExercise(
        date: String,
        saveDailyExerciseRequest: SaveDailyExerciseRequest
    ) = HttpHandler<Unit>()
        .httpRequest { exerciseApi.saveDailyExercise(date, saveDailyExerciseRequest) }
        .sendRequest()

    override suspend fun fetchExerciseRecordList(): ExerciseRecordListResponse =
        HttpHandler<ExerciseRecordListResponse>()
            .httpRequest { exerciseApi.fetchExerciseRecordList() }
            .sendRequest()

    override suspend fun fetchExerciseAnalysisResult(): ExerciseAnalysisResultResponse =
        HttpHandler<ExerciseAnalysisResultResponse>()
            .httpRequest { exerciseApi.fetchExerciseAnalysisResult() }
            .sendRequest()
}