package com.semicolon.data.remote.api

import com.semicolon.data.remote.response.challenge.ChallengeDetailResponse
import com.semicolon.data.remote.response.challenge.ChallengeListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ChallengeApi {
    @GET("challenges/lists")
    suspend fun getChallenges(): ChallengeListResponse

    @GET("challenges/{challenge-id}")
    suspend fun getChallengeDetail(@Path("challenge-id") challengeId: Long): ChallengeDetailResponse
}