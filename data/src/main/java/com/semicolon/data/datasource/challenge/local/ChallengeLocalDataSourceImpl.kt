package com.semicolon.data.datasource.challenge.local

import com.semicolon.data.local.dao.challenge.ChallengeDao
import com.semicolon.data.local.entity.challenge.toDbEntity
import com.semicolon.data.local.entity.challenge.toEntity
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity
import javax.inject.Inject

class ChallengeLocalDataSourceImpl @Inject constructor(
    private val challengeDao: ChallengeDao
): ChallengeLocalDataSource {

    override suspend fun fetchChallenges(): List<ChallengeEntity> =
        challengeDao.fetchChallenges().toEntity()

    override suspend fun saveChallenges(challenges: List<ChallengeEntity>) {
        challengeDao.saveChallenges(challenges.toDbEntity())
    }

    override suspend fun fetchChallengeDetail(id: Int): ChallengeDetailEntity =
        challengeDao.fetchChallengeDetail(id).toEntity()

    override suspend fun saveChallengeDetail(id: Int, detail: ChallengeDetailEntity) {
        challengeDao.saveChallengeDetail(detail.toDbEntity(id))
    }

    override suspend fun fetchParticipants(id: Int): List<ChallengeParticipantEntity> =
        challengeDao.fetchParticipants(id).toEntity()

    override suspend fun saveParticipants(id: Int, participants: List<ChallengeParticipantEntity>) {
        challengeDao.saveParticipants(participants.toDbEntity(id))
    }

}