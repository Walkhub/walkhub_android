package com.semicolon.domain.usecase.challenge

import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.repository.challenge.ChallengeRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow

class FetchChallengeDetailUseCase(private val challengeRepository: ChallengeRepository) :
    UseCase<Int, Flow<ChallengeDetailEntity>>() {
    override suspend fun execute(data: Int): Flow<ChallengeDetailEntity> =
        challengeRepository.fetchChallengeDetail(data)
}