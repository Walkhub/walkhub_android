package com.semicolon.domain.usecase.challenge

import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity
import com.semicolon.domain.repository.ChallengeRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchChallengeParticipantsUseCase @Inject constructor(
    private val challengeRepository: ChallengeRepository
) : UseCase<Int, Flow<List<ChallengeParticipantEntity>>>() {

    override suspend fun execute(data: Int): Flow<List<ChallengeParticipantEntity>> =
        challengeRepository.fetchChallengeParticipants(data)
}