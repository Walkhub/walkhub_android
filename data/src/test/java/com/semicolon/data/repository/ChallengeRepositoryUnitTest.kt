package com.semicolon.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.local.datasource.LocalChallengeDataSource
import com.semicolon.data.remote.datasource.RemoteChallengeDateSource
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.enum.ChallengeGoalScope
import com.semicolon.domain.enum.ChallengeGoalType
import com.semicolon.domain.enum.ChallengeUserScope
import com.semicolon.domain.repository.ChallengeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.threeten.bp.LocalDateTime

class ChallengeRepositoryUnitTest {
    private val remoteChallengeDataSource = mock<RemoteChallengeDateSource>()
    private val localChallengeDateSource = mock<LocalChallengeDataSource>()

    private val challengeRepository: ChallengeRepository = ChallengeRepositoryImpl(
        localChallengeDateSource,
        remoteChallengeDataSource
    )

    @Test
    fun testFetchChallenges() {
        val challengeId = 12
        val challengeEntity = ChallengeEntity(
            challengeId,
            "삼천보걷기",
            LocalDateTime.MIN,
            LocalDateTime.MAX,
            "https://testImageUrl",
            ChallengeUserScope.ALL,
            ChallengeGoalScope.ALL,
            ChallengeGoalType.ETC
        )
        val challengeList = listOf(
            challengeEntity
        )

        runBlocking {
            whenever(localChallengeDateSource.fetchChallenges()).thenReturn(
                challengeList
            )
            whenever(remoteChallengeDataSource.fetchChallenges()).thenReturn(
                challengeList
            )

            val repositoryResult = challengeRepository.fetchChallenges()
            repositoryResult.collect {
                assertEquals(it, challengeList)
            }
        }
    }
}