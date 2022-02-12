package com.semicolon.domain.usecase.challenge

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.enum.ChallengeGoalScope
import com.semicolon.domain.enum.ChallengeGoalType
import com.semicolon.domain.enum.ChallengeUserScope
import com.semicolon.domain.repository.ChallengeRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.threeten.bp.LocalDateTime

class FetchChallengesUseCaseUnitTest {

    private val challengeRepository = mock<ChallengeRepository>()

    private val fetchChallengesUseCase = FetchChallengesUseCase(challengeRepository)

    @Test
    fun testFetchChallengesUseCase() {
        val challengeId = 12
        val challenge = ChallengeEntity(
            challengeId,
            "삼천보 걷기",
            LocalDateTime.MIN,
            LocalDateTime.MAX,
            "https://testImageUrl",
            ChallengeUserScope.ALL,
            ChallengeGoalScope.ALL,
            ChallengeGoalType.ETC
        )
        val challengeList = listOf(
            challenge
        )

        runBlocking {
            whenever(challengeRepository.fetchChallenges()).thenReturn(
                flow {
                    emit(challengeList)
                }
            )

            val useCaseResult = fetchChallengesUseCase.execute(Unit)
            useCaseResult.collect {
                assertEquals(it, challengeList)
            }
        }
    }
}