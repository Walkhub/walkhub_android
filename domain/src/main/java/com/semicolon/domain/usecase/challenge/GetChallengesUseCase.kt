package com.semicolon.domain.usecase.challenge

import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.repository.challenge.ChallengeRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow

class GetChallengesUseCase(private val challengeRepository: ChallengeRepository) :
    UseCase<Unit, Flow<List<ChallengeEntity>>>() {
    override suspend fun execute(data: Unit): Flow<List<ChallengeEntity>> =
        challengeRepository.fetchChallenges()
}