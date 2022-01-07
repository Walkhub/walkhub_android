package com.semicolon.domain.usecase.challenge

import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity
import com.semicolon.domain.repository.challenge.ChallengeRepository
import com.semicolon.domain.usecase.UseCase

class GetChallengeParticipantsUseCase(private val challengeRepository: ChallengeRepository) :
    UseCase<Int, List<ChallengeParticipantEntity>>() {
    override suspend fun execute(data: Int): List<ChallengeParticipantEntity> =
        challengeRepository.fetchChallengeParticipants(data)
}