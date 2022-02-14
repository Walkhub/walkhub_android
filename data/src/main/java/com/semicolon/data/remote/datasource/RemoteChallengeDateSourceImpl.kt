package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.ChallengeApi
import com.semicolon.data.remote.response.challenge.ChallengeDetailResponse
import com.semicolon.data.remote.response.challenge.ChallengeListResponse
import com.semicolon.data.remote.response.challenge.ChallengeParticipantListResponse
import com.semicolon.data.remote.response.challenge.toEntity
import com.semicolon.data.util.HttpHandler
import com.semicolon.domain.entity.challenge.*
import javax.inject.Inject

class RemoteChallengeDateSourceImpl @Inject constructor(
    private val challengeApi: ChallengeApi
) : RemoteChallengeDateSource {
    override suspend fun fetchChallenges(): ChallengeListResponse =
        HttpHandler<ChallengeListResponse>()
            .httpRequest { challengeApi.getChallenges() }
            .sendRequest()

    override suspend fun fetchChallengeDetail(challengeId: Int): ChallengeDetailResponse =
        HttpHandler<ChallengeDetailResponse>()
            .httpRequest { challengeApi.getChallengeDetail(challengeId) }
            .sendRequest()

    override suspend fun postParticipate(challengeId: Int) =
        HttpHandler<Unit>()
            .httpRequest { challengeApi.postParticipateChallenge(challengeId) }
            .sendRequest()


    override suspend fun fetchParticipants(challengeId: Int): ChallengeParticipantListResponse =
        HttpHandler<ChallengeParticipantListResponse>()
            .httpRequest { challengeApi.getChallengeParticipants(challengeId) }
            .sendRequest()
}