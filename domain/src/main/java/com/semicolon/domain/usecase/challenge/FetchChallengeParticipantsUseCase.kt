package com.semicolon.domain.usecase.challenge

import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity
import com.semicolon.domain.repository.challenge.ChallengeRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow

class FetchChallengeParticipantsUseCase(private val challengeRepository: ChallengeRepository) :
    UseCase<Int, Flow<List<ChallengeParticipantEntity>>>() {
    override suspend fun execute(data: Int): Flow<List<ChallengeParticipantEntity>> =
        challengeRepository.fetchChallengeParticipants(data)
}