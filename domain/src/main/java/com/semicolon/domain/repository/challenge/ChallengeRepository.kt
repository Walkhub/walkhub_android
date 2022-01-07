package com.semicolon.domain.repository.challenge

import com.semicolon.domain.entity.challenge.Challenge
import com.semicolon.domain.entity.challenge.ChallengeDetail

interface ChallengeRepository {
    suspend fun fetchChallenges(): List<Challenge>

    suspend fun fetchChallengeDetail(id: Int): ChallengeDetail
}