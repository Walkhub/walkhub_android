package com.semicolon.data.local.datasource

import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity

interface LocalChallengeDataSource {

    suspend fun fetchChallenges(): List<ChallengeEntity>

    suspend fun saveChallenges(challenges: List<ChallengeEntity>)

    suspend fun fetchChallengeDetail(id: Int): ChallengeDetailEntity

    suspend fun saveChallengeDetail(id: Int, detail: ChallengeDetailEntity)

    suspend fun fetchParticipants(id: Int): List<ChallengeParticipantEntity>

    suspend fun saveParticipants(id: Int, participants: List<ChallengeParticipantEntity>)
}