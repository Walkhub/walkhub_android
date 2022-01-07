package com.semicolon.data.repository.challenge

import com.semicolon.data.datasource.challenge.local.ChallengeLocalDataSource
import com.semicolon.data.datasource.challenge.remote.ChallengeRemoteDateSource
import com.semicolon.data.util.OfflineCacheUtil
import com.semicolon.domain.entity.challenge.Challenge
import com.semicolon.domain.entity.challenge.ChallengeDetail
import com.semicolon.domain.entity.challenge.ChallengeParticipant
import com.semicolon.domain.repository.challenge.ChallengeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChallengeRepositoryImpl @Inject constructor(
    private val challengeLocalDataSource: ChallengeLocalDataSource,
    private val challengeRemoteDateSource: ChallengeRemoteDateSource
): ChallengeRepository {
    override suspend fun fetchChallenges(): Flow<List<Challenge>> = withContext(Dispatchers.IO) {
        OfflineCacheUtil<List<Challenge>>()
            .remoteData { challengeRemoteDateSource.fetchChallenges() }
            .createFlow()
    }



    override suspend fun fetchChallengeDetail(id: Int): ChallengeDetail {
        TODO("Not yet implemented")
    }

    override suspend fun fetchChallengeParticipants(id: Int): List<ChallengeParticipant> {
        TODO("Not yet implemented")
    }
}