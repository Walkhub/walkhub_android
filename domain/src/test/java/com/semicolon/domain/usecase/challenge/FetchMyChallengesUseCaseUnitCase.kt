package com.semicolon.domain.usecase.challenge

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.domain.entity.challenge.MyChallengeEntity
import com.semicolon.domain.enums.ChallengeGoalScope
import com.semicolon.domain.enums.ChallengeGoalType
import com.semicolon.domain.repository.ChallengeRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.threeten.bp.LocalDateTime

class FetchMyChallengesUseCaseUnitCase {

    private val challengeRepository = mock<ChallengeRepository>()

    private val fetchMyChallengesUseCase = FetchMyChallengesUseCase(challengeRepository)

    @Test
    fun testFetchMyChallengesUseCase() {
        val challengeId = 12
        val challengeEntity = MyChallengeEntity(
            challengeId,
            "삼천보걷기",
            LocalDateTime.MIN,
            LocalDateTime.MAX,
            "https://testImageUrl",
            goalScope = ChallengeGoalScope.ALL,
            goalType = ChallengeGoalType.ETC,
            goal = 3000,
            writer = MyChallengeEntity.Writer(
                12,
                "JaeWon",
                "https://testImageUrl"
            ),
            totalWalkCount = 10000
        )

        val challengeList = listOf(
            challengeEntity
        )

        runBlocking {
            whenever(challengeRepository.fetchMyChallenges()).thenReturn(
                flow {
                    emit(challengeList)
                }
            )

            val useCaseResult = fetchMyChallengesUseCase.execute(Unit)
            useCaseResult.collect {
                assertEquals(it, challengeList)
            }
        }
    }
}