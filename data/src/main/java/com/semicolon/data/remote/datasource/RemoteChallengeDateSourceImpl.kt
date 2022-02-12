package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.ChallengeApi
import com.semicolon.data.remote.response.challenge.ChallengeDetailResponse
import com.semicolon.data.remote.response.challenge.ChallengeListResponse
import com.semicolon.data.remote.response.challenge.ChallengeParticipantListResponse
import com.semicolon.data.remote.response.challenge.toEntity
import com.semicolon.data.util.HttpHandler
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity
import javax.inject.Inject

class RemoteChallengeDateSourceImpl @Inject constructor(
    private val challengeApi: ChallengeApi
) : RemoteChallengeDateSource {
    override suspend fun fetchChallenges(): List<ChallengeEntity> =
        HttpHandler<ChallengeListResponse>()
            .httpRequest { challengeApi.getChallenges() }
            .sendRequest().toEntity()

    override suspend fun fetchChallengeDetail(challengeId: Int): ChallengeDetailEntity =
        HttpHandler<ChallengeDetailResponse>()
            .httpRequest { challengeApi.getChallengeDetail(challengeId) }
            .sendRequest().toEntity()

    override suspend fun postParticipate(challengeId: Int) =
        HttpHandler<Unit>()
            .httpRequest { challengeApi.postParticipateChallenge(challengeId) }
            .sendRequest()


    override suspend fun fetchParticipants(challengeId: Int): List<ChallengeParticipantEntity> =
        HttpHandler<ChallengeParticipantListResponse>()
            .httpRequest { challengeApi.getChallengeParticipants(challengeId) }
            .sendRequest().toEntity()

    override suspend fun fetchMyChallenges(): List<ChallengeEntity> =
        HttpHandler<ChallengeListResponse>()
            .httpRequest { challengeApi.getMyChallenges() }
            .sendRequest().toEntity()
}