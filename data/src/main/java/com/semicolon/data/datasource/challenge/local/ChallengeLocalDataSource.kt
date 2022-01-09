package com.semicolon.data.datasource.challenge.local

import com.semicolon.domain.entity.challenge.ChallengeEntity
import kotlinx.coroutines.flow.Flow

interface ChallengeLocalDataSource {

    suspend fun fetchChallenges(): Flow<List<ChallengeEntity>>
}