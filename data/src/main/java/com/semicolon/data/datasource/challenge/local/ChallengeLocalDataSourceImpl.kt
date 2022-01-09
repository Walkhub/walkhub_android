package com.semicolon.data.datasource.challenge.local

import com.semicolon.domain.entity.challenge.ChallengeEntity
import kotlinx.coroutines.flow.Flow

class ChallengeLocalDataSourceImpl: ChallengeLocalDataSource {
    override suspend fun fetchChallenges(): List<ChallengeEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun saveChallenges(challenges: List<ChallengeEntity>) {
        TODO("Not yet implemented")
    }
}