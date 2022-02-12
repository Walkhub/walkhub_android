package com.semicolon.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.local.datasource.LocalChallengeDataSource
import com.semicolon.data.remote.datasource.RemoteChallengeDateSource
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity
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

    @Test
    fun testFetchChallengeDetail() {
        val challengeId = 12
        val challengeDetailEntity = ChallengeDetailEntity(
            "삼천보걷기",
            "삼천보 겉기가 목표입니다",
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
                13,
                "김재원",
                "https://testImageUrl"
            )
        )
        runBlocking {
            whenever(localChallengeDateSource.fetchChallengeDetail(challengeId)).thenReturn(
                challengeDetailEntity
            )
            whenever(remoteChallengeDataSource.fetchChallengeDetail(challengeId)).thenReturn(
                challengeDetailEntity
            )

            val repositoryResultFlow = challengeRepository.fetchChallengeDetail(challengeId)
            repositoryResultFlow.collect {
                assertEquals(it, challengeDetailEntity)
            }
        }
    }

    @Test
    fun testFetchChallengeParticipants() {
        val challengeId = 12
        val participantEntity = ChallengeParticipantEntity(
            13,
            "김재원",
            "https://testImageUrl"
        )
        val participantList = listOf(
            participantEntity
        )

        runBlocking {
            whenever(localChallengeDateSource.fetchParticipants(challengeId)).thenReturn(
                participantList
            )
            whenever(remoteChallengeDataSource.fetchParticipants(challengeId)).thenReturn(
                participantList
            )

            val repositoryResult = challengeRepository.fetchChallengeParticipants(challengeId)
            repositoryResult.collect {
                assertEquals(it, participantList)
            }
        }
    }

    @Test
    fun testPostParticipateChallenge() {
        val challengeId = 12
        runBlocking {
            val repositoryResult = challengeRepository.postParticipateChallenge(challengeId)
            assertEquals(repositoryResult, Unit)
        }
    }
}