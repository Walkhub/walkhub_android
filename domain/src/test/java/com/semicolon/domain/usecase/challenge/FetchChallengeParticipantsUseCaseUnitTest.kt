package com.semicolon.domain.usecase.challenge

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity
import com.semicolon.domain.repository.ChallengeRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchChallengeParticipantsUseCaseUnitTest {

    private val challengeRepository = mock<ChallengeRepository>()

    private val fetchChallengeParticipantsUseCase = FetchChallengeParticipantsUseCase(challengeRepository)

    @Test
    fun testFetchChallengeParticipantUseCase() {
        val challengeId = 12
        val participant = ChallengeParticipantEntity(
            13,
            "김재원",
            "https://testImageUrl"
        )

        val participantList = listOf(
            participant
        )

        runBlocking {
            whenever(challengeRepository.fetchChallengeParticipants(challengeId)).thenReturn(
                flow {
                    emit(participantList)
                }
            )

            val useCaseResult = fetchChallengeParticipantsUseCase.execute(challengeId)
            useCaseResult.collect {
                assertEquals(it, participantList)
            }
        }
    }
}