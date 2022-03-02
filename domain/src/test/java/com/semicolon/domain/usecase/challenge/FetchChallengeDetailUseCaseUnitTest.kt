package com.semicolon.domain.usecase.challenge

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.enums.ChallengeGoalScope
import com.semicolon.domain.enums.ChallengeGoalType
import com.semicolon.domain.enums.ChallengeUserScope
import com.semicolon.domain.repository.ChallengeRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.threeten.bp.LocalDateTime

class FetchChallengeDetailUseCaseUnitTest {

    private val challengeRepository = mock<ChallengeRepository>()

    private val fetchChallengeDetailUseCase = FetchChallengeDetailUseCase(challengeRepository)

    @Test
    fun testFetchChallengeDetailUseCae() {
        val challengeId = 12
        val challengeDetail = ChallengeDetailEntity(
            "삼천보 걷기",
            "삼천보 걷기가 목표입니다",
            3000,
            ChallengeGoalType.ETC,
            ChallengeGoalScope.ALL,
            ChallengeUserScope.ALL,
            "수행평가 만점",
            "https://testImageUrl",
            LocalDateTime.MIN,
            LocalDateTime.MAX,
            false,
            10,
            ChallengeDetailEntity.WriterEntity(
                10,
                "김재원",
                "https:/testImageUrl"
            )
        )

        runBlocking {
            whenever(challengeRepository.fetchChallengeDetail(challengeId)).thenReturn(
                flow {
                    emit(challengeDetail)
                }
            )

            val useCaseResult = fetchChallengeDetailUseCase.execute(challengeId)
            useCaseResult.collect {
                assertEquals(it, challengeDetail)
            }
        }
    }
}