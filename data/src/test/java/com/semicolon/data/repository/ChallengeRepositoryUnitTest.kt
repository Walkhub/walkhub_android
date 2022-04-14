package com.semicolon.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.local.datasource.LocalChallengeDataSource
import com.semicolon.data.remote.datasource.RemoteChallengeDateSource
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity
import com.semicolon.domain.entity.challenge.MyChallengeEntity
import com.semicolon.domain.enums.ChallengeGoalScope
import com.semicolon.domain.enums.ChallengeGoalType
import com.semicolon.domain.enums.ChallengeUserScope
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

    private val challengeEntity = ChallengeEntity(
        12,
        "삼천보걷기",
        LocalDateTime.MIN,
        LocalDateTime.MAX,
        "https://testImageUrl",
        goalScope = ChallengeGoalScope.ALL,
        goalType = ChallengeGoalType.ETC,
        award = "award",
        writer = ChallengeEntity.Writer(
            12L,
            "JaeWon",
            "https://testImageUrl"
        ),
        participantCount = 12,
        participantList = listOf(
            ChallengeParticipantEntity(
                12L,
                "JaeWon",
                "https://testImageUrl"
            )
        ),
        goal = 3000
    )

    private val myChallengeList = listOf(
        MyChallengeEntity(
            1,
            "삼천보걷기",
            LocalDateTime.MIN,
            LocalDateTime.MAX,
            "https://testImageUrl",
            3000,
            goalScope = ChallengeGoalScope.ALL,
            goalType = ChallengeGoalType.ETC,
            writer = MyChallengeEntity.Writer(
                13L,
                "김재원",
                "https://testImageUrl"
            ),
            totalWalkCount = 3000
        )
    )

    private val challengeList = listOf(
        challengeEntity
    )

    @Test
    fun testFetchChallenges() {
        runBlocking {
            whenever(localChallengeDateSource.fetchChallenges()).thenReturn(
                challengeList
            )
            whenever(remoteChallengeDataSource.fetchChallenges()).thenReturn(
                challengeList
            )

            val repositoryResult = challengeRepository.fetchChallenges()
            repositoryResult.collect {
                assertEquals(challengeList, it)
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
                assertEquals(challengeDetailEntity, it)
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
                assertEquals(participantList, it)
            }
        }
    }

    @Test
    fun testPostParticipateChallenge() {
        val challengeId = 12
        runBlocking {
            val repositoryResult = challengeRepository.postParticipateChallenge(challengeId)
            assertEquals(Unit, repositoryResult)
        }
    }

    @Test
    fun testFetchMyChallenges() {
        runBlocking {
            whenever(remoteChallengeDataSource.fetchMyChallenges()).thenReturn(
                myChallengeList
            )

            val repositoryResult = challengeRepository.fetchMyChallenges()
            repositoryResult.collect {
                assertEquals(myChallengeList, it)
            }
        }
    }
}