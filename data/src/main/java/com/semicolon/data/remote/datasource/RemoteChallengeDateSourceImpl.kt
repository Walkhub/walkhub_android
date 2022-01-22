package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.ChallengeApi
import com.semicolon.data.remote.response.challenge.toEntity
import com.semicolon.domain.entity.challenge.*
import javax.inject.Inject

class RemoteChallengeDateSourceImpl @Inject constructor(
    private val challengeApi: ChallengeApi
) : RemoteChallengeDateSource {
    override suspend fun fetchChallenges(): List<ChallengeEntity> =
        challengeApi.getChallenges().toEntity()

    override suspend fun fetchChallengeDetail(challengeId: Int): ChallengeDetailEntity =
        challengeApi.getChallengeDetail(challengeId).toEntity()

    override suspend fun postParticipate(challengeId: Int) =
        challengeApi.postParticipateChallenge(challengeId)

    override suspend fun fetchParticipants(challengeId: Int): List<ChallengeParticipantEntity> =
        challengeApi.getChallengeParticipants(challengeId).toEntity()
}