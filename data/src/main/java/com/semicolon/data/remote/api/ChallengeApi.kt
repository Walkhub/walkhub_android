package com.semicolon.data.remote.api

import com.semicolon.data.remote.response.challenge.ChallengeDetailResponse
import com.semicolon.data.remote.response.challenge.ChallengeListResponse
import com.semicolon.data.remote.response.challenge.ChallengeParticipantListResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChallengeApi {
    @GET("challenges/lists")
    suspend fun getChallenges(): ChallengeListResponse

    @GET("challenges/{challenge-id}")
    suspend fun getChallengeDetail(@Path("challenge-id") challengeId: Long): ChallengeDetailResponse

    @POST("challenges/{challenge-id}")
    suspend fun postParticipateChallenge(@Path("challenge-id") challengeId: Long)

    @GET("challenges/{challenge-id}/participants")
    suspend fun getChallengeParticipants(@Path("challenge-id") challengeId: Long): ChallengeParticipantListResponse
}