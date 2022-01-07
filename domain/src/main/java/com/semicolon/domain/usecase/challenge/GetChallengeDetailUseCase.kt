package com.semicolon.domain.usecase.challenge

import com.semicolon.domain.entity.challenge.ChallengeDetail
import com.semicolon.domain.repository.challenge.ChallengeRepository
import com.semicolon.domain.usecase.UseCase

class GetChallengeDetailUseCase(private val challengeRepository: ChallengeRepository) :
    UseCase<Int, ChallengeDetail>() {
    override suspend fun execute(data: Int): ChallengeDetail =
        challengeRepository.fetchChallengeDetail(data)
}