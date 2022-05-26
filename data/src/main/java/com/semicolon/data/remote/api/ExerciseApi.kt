package com.semicolon.data.remote.api

import com.semicolon.data.remote.request.exercise.FinishMeasureExerciseRequest
import com.semicolon.data.remote.request.exercise.SaveDailyExerciseRequest
import com.semicolon.data.remote.request.exercise.SendLocationRecordsRequest
import com.semicolon.data.remote.request.exercise.StartMeasureExerciseRequest
import com.semicolon.data.remote.response.exercise.ExerciseAnalysisResultResponse
import com.semicolon.data.remote.response.exercise.ExerciseIdResponse
import com.semicolon.data.remote.response.exercise.ExerciseRecordListResponse
import com.semicolon.data.remote.response.exercise.ExercisingUserListResponse
import retrofit2.Response
import retrofit2.http.*

interface ExerciseApi {

    @POST("exercises")
    suspend fun startMeasureExercise(
        @Body startMeasureExerciseRequest: StartMeasureExerciseRequest
    ): ExerciseIdResponse

    @PATCH("exercises/{exerciseId}")
    suspend fun finishMeasureExercise(
        @Path("exerciseId") exerciseId: Int,
        @Body finishMeasureExerciseRequest: FinishMeasureExerciseRequest
    ): Response<Unit>

    @POST("exercises/locations/{exerciseId}")
    suspend fun sendLocationRecords(
        @Path("exerciseId") exerciseId: Int,
        @Body sendLocationRecordsRequest: SendLocationRecordsRequest
    )

    @PUT("exercises")
    suspend fun saveDailyExercise(
        @Body saveDailyExerciseRequest: SaveDailyExerciseRequest
    ): Response<Unit>

    @GET("exercises/lists")
    suspend fun fetchExerciseRecordList(): ExerciseRecordListResponse

    @GET("exercises/analysis")
    suspend fun fetchExerciseAnalysisResult(): ExerciseAnalysisResultResponse

    @GET("exercises/users/lists")
    suspend fun fetchExercisingUserList(): ExercisingUserListResponse
}