package com.semicolon.domain.usecase.challenge

import com.semicolon.domain.entity.challenge.Challenge
import com.semicolon.domain.repository.challenge.ChallengeRepository
import com.semicolon.domain.usecase.UseCase

class GetChallengesUseCase(private val challengeRepository: ChallengeRepository) :
    UseCase<Unit, List<Challenge>>() {
    override suspend fun execute(data: Unit): List<Challenge> =
        challengeRepository.fetchChallenges()
}