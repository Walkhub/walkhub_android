package com.semicolon.domain.repository.challenge

import com.semicolon.domain.entity.challenge.Challenge
import com.semicolon.domain.entity.challenge.ChallengeDetail
import com.semicolon.domain.entity.challenge.ChallengeParticipant

interface ChallengeRepository {
    suspend fun fetchChallenges(): List<Challenge>

    suspend fun fetchChallengeDetail(id: Int): ChallengeDetail

    suspend fun fetchChallengeParticipants(id: Int): List<ChallengeParticipant>
}