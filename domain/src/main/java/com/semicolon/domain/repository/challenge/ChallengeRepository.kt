package com.semicolon.domain.repository.challenge

import com.semicolon.domain.entity.challenge.Challenge

interface ChallengeRepository {
    suspend fun fetchChallenges(): List<Challenge>
}