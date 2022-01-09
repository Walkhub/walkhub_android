package com.semicolon.data.datasource.challenge.local

import com.semicolon.data.local.database.challenge.ChallengeDao
import com.semicolon.domain.entity.challenge.ChallengeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChallengeLocalDataSourceImpl @Inject constructor(
    private val challengeDao: ChallengeDao
): ChallengeLocalDataSource {
    override suspend fun fetchChallenges(): List<ChallengeEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun saveChallenges(challenges: List<ChallengeEntity>) {
        TODO("Not yet implemented")
    }
}