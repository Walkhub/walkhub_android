package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.response.challenge.ChallengeDetailResponse
import com.semicolon.data.remote.response.challenge.ChallengeListResponse
import com.semicolon.data.remote.response.challenge.ChallengeParticipantListResponse

interface RemoteChallengeDateSource {
    suspend fun fetchChallenges(): ChallengeListResponse

    suspend fun fetchChallengeDetail(challengeId: Int): ChallengeDetailResponse

    suspend fun postParticipate(challengeId: Int)

    suspend fun fetchParticipants(challengeId: Int): ChallengeParticipantListResponse
}