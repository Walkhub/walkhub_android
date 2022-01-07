package com.semicolon.domain.usecase.challenge

import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.repository.challenge.ChallengeRepository
import com.semicolon.domain.usecase.UseCase

class GetChallengeDetailUseCase(private val challengeRepository: ChallengeRepository) :
    UseCase<Int, ChallengeDetailEntity>() {
    override suspend fun execute(data: Int): ChallengeDetailEntity =
        challengeRepository.fetchChallengeDetail(data)
}