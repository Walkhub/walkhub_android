package com.semicolon.data.remote.api

import com.semicolon.data.remote.response.level.LevelListResponse
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface LevelApi {

    @GET("levels/lists")
    fun fetchLevelList(): LevelListResponse

    @PATCH("levels/{level-id}")
    fun patchMaxLevel(@Path("level-id") levelId: Int)
}