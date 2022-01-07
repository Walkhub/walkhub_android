package com.semicolon.data.repository.challenge

import com.semicolon.domain.entity.challenge.Challenge
import com.semicolon.domain.entity.challenge.ChallengeDetail
import com.semicolon.domain.entity.challenge.ChallengeParticipant
import com.semicolon.domain.repository.challenge.ChallengeRepository

class ChallengeRepositoryImpl: ChallengeRepository {
    override suspend fun fetchChallenges(): List<Challenge> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchChallengeDetail(id: Int): ChallengeDetail {
        TODO("Not yet implemented")
    }

    override suspend fun fetchChallengeParticipants(id: Int): List<ChallengeParticipant> {
        TODO("Not yet implemented")
    }
}