package com.semicolon.domain.usecase.challenge

import com.semicolon.domain.entity.challenge.MyChallengeEntity
import com.semicolon.domain.repository.ChallengeRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchMyChallengesUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository
) : UseCase<Unit, Flow<List<MyChallengeEntity>>>() {

    override suspend fun execute(data: Unit): Flow<List<MyChallengeEntity>> =
        challengeRepository.fetchMyChallenges()
}