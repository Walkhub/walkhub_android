package com.semicolon.data.repository

import com.semicolon.data.local.datasource.LocalChallengeDataSource
import com.semicolon.data.remote.datasource.RemoteChallengeDateSource
import com.semicolon.data.remote.response.challenge.toEntity
import com.semicolon.data.util.OfflineCacheUtil
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity
import com.semicolon.domain.repository.ChallengeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChallengeRepositoryImpl @Inject constructor(
    private val localChallengeDataSource: LocalChallengeDataSource,
    private val remoteChallengeDateSource: RemoteChallengeDateSource
) : ChallengeRepository {

    override suspend fun fetchChallenges(): Flow<List<ChallengeEntity>> =
        OfflineCacheUtil<List<ChallengeEntity>>()
            .remoteData { remoteChallengeDateSource.fetchChallenges().toEntity() }
            .localData { localChallengeDataSource.fetchChallenges() }
            .compareData { localData, remoteData -> localData.containsAll(remoteData) }
            .doOnNeedRefresh { localChallengeDataSource.saveChallenges(it) }
            .createFlow()

    override suspend fun fetchChallengeDetail(id: Int): Flow<ChallengeDetailEntity> =
        OfflineCacheUtil<ChallengeDetailEntity>()
            .remoteData { remoteChallengeDateSource.fetchChallengeDetail(id).toEntity() }
            .localData { localChallengeDataSource.fetchChallengeDetail(id) }
            .doOnNeedRefresh { localChallengeDataSource.saveChallengeDetail(id, it) }
            .createFlow()

    override suspend fun fetchChallengeParticipants(id: Int): Flow<List<ChallengeParticipantEntity>> =
        OfflineCacheUtil<List<ChallengeParticipantEntity>>()
            .remoteData { remoteChallengeDateSource.fetchParticipants(id).toEntity() }
            .localData { localChallengeDataSource.fetchParticipants(id) }
            .doOnNeedRefresh { localChallengeDataSource.saveParticipants(id, it) }
            .createFlow()

    override suspend fun postParticipateChallenge(id: Int) =
        remoteChallengeDateSource.postParticipate(id)

    override suspend fun fetchMyChallenges(): Flow<List<ChallengeEntity>> =
        flow {
            emit(remoteChallengeDateSource.fetchMyChallenges().toEntity())
        }
}