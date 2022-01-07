package com.semicolon.data.datasource.challenge.remote

import com.semicolon.data.remote.api.ChallengeApi
import com.semicolon.data.remote.response.challenge.toEntity
import com.semicolon.domain.entity.challenge.Challenge
import com.semicolon.domain.entity.challenge.ChallengeDetail
import com.semicolon.domain.entity.challenge.ChallengeParticipant

class ChallengeRemoteDataSourceImpl(private val challengeApi: ChallengeApi): ChallengeRemoteDateSource {
    override suspend fun fetchChallenges(): List<Challenge> =
        challengeApi.getChallenges().toEntity()

    override suspend fun fetchChallengeDetail(challengeId: Int): ChallengeDetail =
        challengeApi.getChallengeDetail(challengeId).toEntity()

    override suspend fun postParticipate(challengeId: Int) =
        challengeApi.postParticipateChallenge(challengeId)

    override suspend fun fetchParticipants(challengeId: Int): List<ChallengeParticipant> {
        TODO("Not yet implemented")
    }
}