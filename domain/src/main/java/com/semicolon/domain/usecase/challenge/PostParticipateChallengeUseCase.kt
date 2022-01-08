package com.semicolon.domain.usecase.challenge

import com.semicolon.domain.repository.challenge.ChallengeRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class PostParticipateChallengeUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository
): UseCase<Int, Unit>() {

    override suspend fun execute(data: Int) {
        challengeRepository.postParticipateChallenge(data)
    }
}