package com.semicolon.data.remote.api

import retrofit2.http.GET

interface ChallengeApi {
    @GET("challenges/lists")
    suspend fun getChallenges()
}