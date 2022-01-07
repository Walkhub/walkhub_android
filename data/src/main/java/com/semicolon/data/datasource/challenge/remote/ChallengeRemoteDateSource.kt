package com.semicolon.data.datasource.challenge.remote

import com.semicolon.domain.entity.challenge.Challenge
import com.semicolon.domain.entity.challenge.ChallengeDetail
import com.semicolon.domain.entity.challenge.ChallengeParticipant

interface ChallengeRemoteDateSource {
    suspend fun fetchChallenges(): List<Challenge>

    suspend fun fetchChallengeDetail(challengeId: Int): ChallengeDetail

    suspend fun postParticipate(challengeId: Int)

    suspend fun fetchParticipants(challengeId: Int): List<ChallengeParticipant>
}