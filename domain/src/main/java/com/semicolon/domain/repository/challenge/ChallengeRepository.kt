package com.semicolon.domain.repository.challenge

import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity
import kotlinx.coroutines.flow.Flow

interface ChallengeRepository {
    suspend fun fetchChallenges(): Flow<List<ChallengeEntity>>

    suspend fun fetchChallengeDetail(id: Int): Flow<ChallengeDetailEntity>

    suspend fun fetchChallengeParticipants(id: Int): Flow<List<ChallengeParticipantEntity>>
}