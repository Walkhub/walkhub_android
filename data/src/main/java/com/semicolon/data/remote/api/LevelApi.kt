package com.semicolon.data.remote.api

import com.semicolon.data.remote.response.level.LevelListResponse
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface LevelApi {

    @GET("levels/lists")
    suspend fun fetchLevelList(): LevelListResponse

    @PATCH("levels/{level-id}")
    suspend fun patchMaxLevel(@Path("level-id") levelId: Int)
}