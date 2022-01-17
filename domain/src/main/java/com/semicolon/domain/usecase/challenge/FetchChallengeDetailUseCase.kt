package com.semicolon.domain.usecase.challenge

import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.repository.ChallengeRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchChallengeDetailUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository
) : UseCase<Int, Flow<ChallengeDetailEntity>>() {

    override suspend fun execute(data: Int): Flow<ChallengeDetailEntity> =
        challengeRepository.fetchChallengeDetail(data)
}