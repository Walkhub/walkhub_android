package com.semicolon.data.repository.challenge

import com.semicolon.data.datasource.challenge.local.ChallengeLocalDataSource
import com.semicolon.data.datasource.challenge.remote.ChallengeRemoteDateSource
import com.semicolon.data.util.OfflineCacheUtil
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity
import com.semicolon.domain.repository.challenge.ChallengeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChallengeRepositoryImpl @Inject constructor(
    private val challengeLocalDataSource: ChallengeLocalDataSource,
    private val challengeRemoteDateSource: ChallengeRemoteDateSource
) : ChallengeRepository {

    override suspend fun fetchChallenges(): Flow<List<ChallengeEntity>> =
        OfflineCacheUtil<List<ChallengeEntity>>()
            .remoteData { challengeRemoteDateSource.fetchChallenges() }
            .localData { challengeLocalDataSource.fetchChallenges() }
            .compareData { localData, remoteData -> localData.containsAll(remoteData) }
            .doOnNeedRefresh { challengeLocalDataSource.saveChallenges(it) }
            .createFlow()


    override suspend fun fetchChallengeDetail(id: Int): Flow<ChallengeDetailEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchChallengeParticipants(id: Int): Flow<List<ChallengeParticipantEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun postParticipateChallenge(id: Int) {
        TODO("Not yet implemented")
    }
}