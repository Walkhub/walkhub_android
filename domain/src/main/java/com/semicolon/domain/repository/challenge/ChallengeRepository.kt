package com.semicolon.domain.repository.challenge

import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity

interface ChallengeRepository {
    suspend fun fetchChallenges(): List<ChallengeEntity>

    suspend fun fetchChallengeDetail(id: Int): ChallengeDetailEntity

    suspend fun fetchChallengeParticipants(id: Int): List<ChallengeParticipantEntity>
}