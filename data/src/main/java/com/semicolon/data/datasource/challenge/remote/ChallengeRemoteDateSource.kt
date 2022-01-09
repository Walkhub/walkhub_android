package com.semicolon.data.datasource.challenge.remote

import com.semicolon.domain.entity.challenge.*

interface ChallengeRemoteDateSource {
    suspend fun fetchChallenges(): List<ChallengeEntity>

    suspend fun fetchChallengeDetail(challengeId: Int): ChallengeDetailEntity

    suspend fun postParticipate(challengeId: Int)

    suspend fun fetchParticipants(challengeId: Int): List<ChallengeParticipantEntity>
}