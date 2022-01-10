package com.semicolon.data.datasource.challenge.local

import com.semicolon.data.local.database.challenge.ChallengeDao
import com.semicolon.data.local.database.challenge.entity.toDatabaseEntity
import com.semicolon.data.local.database.challenge.entity.toEntity
import com.semicolon.domain.entity.challenge.ChallengeEntity
import javax.inject.Inject

class ChallengeLocalDataSourceImpl @Inject constructor(
    private val challengeDao: ChallengeDao
): ChallengeLocalDataSource {
    override suspend fun fetchChallenges(): List<ChallengeEntity> =
        challengeDao.fetchChallenges().toEntity()

    override suspend fun saveChallenges(challenges: List<ChallengeEntity>) {
        challengeDao.saveChallenges(challenges.toDatabaseEntity())
    }
}