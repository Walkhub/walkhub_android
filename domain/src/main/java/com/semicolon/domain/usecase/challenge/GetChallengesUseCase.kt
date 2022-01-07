package com.semicolon.domain.usecase.challenge

import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.repository.challenge.ChallengeRepository
import com.semicolon.domain.usecase.UseCase

class GetChallengesUseCase(private val challengeRepository: ChallengeRepository) :
    UseCase<Unit, List<ChallengeEntity>>() {
    override suspend fun execute(data: Unit): List<ChallengeEntity> =
        challengeRepository.fetchChallenges()
}