package com.semicolon.data.datasource.challenge.local

import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.entity.challenge.ChallengeEntity

interface ChallengeLocalDataSource {

    suspend fun fetchChallenges(): List<ChallengeEntity>

    suspend fun saveChallenges(challenges: List<ChallengeEntity>)

    suspend fun fetchChallengeDetail(id: Int): ChallengeDetailEntity

    suspend fun saveChallengeDetail(id: Int, detail: ChallengeDetailEntity)
}