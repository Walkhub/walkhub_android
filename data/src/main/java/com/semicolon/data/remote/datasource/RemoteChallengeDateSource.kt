package com.semicolon.data.remote.datasource

import com.semicolon.domain.entity.challenge.*

interface RemoteChallengeDateSource {
    suspend fun fetchChallenges(): List<ChallengeEntity>

    suspend fun fetchChallengeDetail(challengeId: Int): ChallengeDetailEntity

    suspend fun postParticipate(challengeId: Int)

    suspend fun fetchParticipants(challengeId: Int): List<ChallengeParticipantEntity>
}