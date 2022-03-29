package com.semicolon.data.remote.datasource

import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity
import com.semicolon.domain.entity.challenge.MyChallengeEntity

interface RemoteChallengeDateSource {
    suspend fun fetchChallenges(): List<ChallengeEntity>

    suspend fun fetchChallengeDetail(challengeId: Int): ChallengeDetailEntity

    suspend fun postParticipate(challengeId: Int)

    suspend fun fetchParticipants(challengeId: Int): List<ChallengeParticipantEntity>

    suspend fun fetchMyChallenges(): List<MyChallengeEntity>
}