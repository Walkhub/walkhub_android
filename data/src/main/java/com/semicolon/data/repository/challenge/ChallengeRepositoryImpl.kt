package com.semicolon.data.repository.challenge

import com.semicolon.data.local.datasource.challenge.ChallengeLocalDataSource
import com.semicolon.data.remote.datasource.challenge.ChallengeRemoteDateSource
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

    override suspend fun fetchChallengeDetail(id: Int): Flow<ChallengeDetailEntity> =
        OfflineCacheUtil<ChallengeDetailEntity>()
            .remoteData { challengeRemoteDateSource.fetchChallengeDetail(id) }
            .localData { challengeLocalDataSource.fetchChallengeDetail(id) }
            .doOnNeedRefresh { challengeLocalDataSource.saveChallengeDetail(id, it) }
            .createFlow()

    override suspend fun fetchChallengeParticipants(id: Int): Flow<List<ChallengeParticipantEntity>> =
        OfflineCacheUtil<List<ChallengeParticipantEntity>>()
            .remoteData { challengeRemoteDateSource.fetchParticipants(id) }
            .localData { challengeLocalDataSource.fetchParticipants(id) }
            .doOnNeedRefresh { challengeLocalDataSource.saveParticipants(id, it) }
            .createFlow()

    override suspend fun postParticipateChallenge(id: Int) {
        challengeRemoteDateSource.postParticipate(id)
    }
}