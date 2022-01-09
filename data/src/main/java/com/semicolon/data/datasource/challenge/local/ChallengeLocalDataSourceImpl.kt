package com.semicolon.data.datasource.challenge.local

import com.semicolon.domain.entity.challenge.ChallengeEntity
import kotlinx.coroutines.flow.Flow

class ChallengeLocalDataSourceImpl: ChallengeLocalDataSource {
    override suspend fun fetchChallenges(): Flow<List<ChallengeEntity>> {
        TODO("Not yet implemented")
    }
}