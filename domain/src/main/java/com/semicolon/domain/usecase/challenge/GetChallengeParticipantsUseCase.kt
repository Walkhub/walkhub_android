package com.semicolon.domain.usecase.challenge

import com.semicolon.domain.entity.challenge.ChallengeParticipant
import com.semicolon.domain.repository.challenge.ChallengeRepository
import com.semicolon.domain.usecase.UseCase

class GetChallengeParticipantsUseCase(private val challengeRepository: ChallengeRepository) :
    UseCase<Int, List<ChallengeParticipant>>() {
    override suspend fun execute(data: Int): List<ChallengeParticipant> =
        challengeRepository.fetchChallengeParticipants(data)
}