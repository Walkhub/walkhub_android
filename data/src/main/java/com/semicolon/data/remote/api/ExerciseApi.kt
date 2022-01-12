package com.semicolon.data.remote.api

import com.semicolon.data.remote.request.exercise.FinishMeasureExerciseRequest
import com.semicolon.data.remote.request.exercise.SaveDailyExerciseRequest
import com.semicolon.data.remote.request.exercise.SendLocationRecordsRequest
import com.semicolon.data.remote.request.exercise.StartMeasureExerciseRequest
import com.semicolon.data.remote.response.exercise.ExerciseIdResponse
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
    )

    @POST("locations/{exerciseId}")
    suspend fun sendLocationRecords(
        @Path("exerciseId") exerciseId: Int,
        @Body sendLocationRecordsRequest: SendLocationRecordsRequest
    )

    @PUT("exercises")
    suspend fun saveDailyExercise(
        @Query("date") date: String,
        @Body saveDailyExerciseRequest: SaveDailyExerciseRequest
    )
}