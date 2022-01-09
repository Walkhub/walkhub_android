package com.semicolon.data.datasource.challenge.local

import com.semicolon.domain.entity.challenge.ChallengeEntity

interface ChallengeLocalDataSource {

    suspend fun fetchChallenges(): List<ChallengeEntity>

    suspend fun saveChallenges(challenges: List<ChallengeEntity>)
}