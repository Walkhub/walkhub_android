package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.local.dao.ChallengeDao
import com.semicolon.data.local.datasource.LocalChallengeDataSource
import com.semicolon.data.local.datasource.LocalChallengeDataSourceImpl
import com.semicolon.data.local.entity.challenge.*
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.enum.ChallengeGoalScope
import com.semicolon.domain.enum.ChallengeGoalType
import com.semicolon.domain.enum.ChallengeUserScope
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.threeten.bp.LocalDateTime

class LocalChallengeDataSourceUnitTest {

    private val challengeDao = mock<ChallengeDao>()

    private val localChallengeDataSource: LocalChallengeDataSource =
        LocalChallengeDataSourceImpl(challengeDao)

    private val challengeRoomEntity = ChallengeRoomEntity(
        1,
        "삼천보걷기",
        "2022-12-12T12:12",
        "2022-12-12T12:12",
        "https://testImageUrl",
        "ALL",
        "ALL",
        "WALK"
    )

    private val challengeRoomList = listOf(
        challengeRoomEntity
    )

    private val challengeDetailRoomEntity = ChallengeDetailRoomEntity(
        12,
        "삼천보걷기",
        3000,
        "ALL",
        "ALL",
        "ALL",
        "수행평가 만점",
        false,
        10,
        13,
        "김재원",
        "https://testImageUrl"
    )

    @Test
    fun testFetchChallenges() {
        runBlocking {
            whenever(challengeDao.fetchChallenges()).thenReturn(challengeRoomList)

            val dataSource = localChallengeDataSource.fetchChallenges()
            assertEquals(dataSource, challengeRoomList.toEntity())
        }
    }

    @Test
    fun testSaveChallenges() {
        runBlocking {
            val dataSource = localChallengeDataSource.saveChallenges(challengeRoomList.toEntity())
            assertEquals(dataSource, Unit)
        }
    }

    @Test
    fun testFetchChallengeDetail() {
        val challengeId = 12
        val challengeAndDetail = ChallengeAndDetail(
            challengeRoomEntity,
            challengeDetailRoomEntity
        )
        runBlocking {
            whenever(challengeDao.fetchChallengeDetail(challengeId)).thenReturn(
                challengeAndDetail
            )
            val dataSource = localChallengeDataSource.fetchChallengeDetail(challengeId)
            assertEquals(dataSource, challengeAndDetail.toEntity())
        }
    }

    @Test
    fun testSaveChallengeDetail() {
        val challengeDetailEntity = ChallengeDetailEntity(
            "삼천보 걷기",
            "삼천보걷기가 목표입니다.",
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
        val challengeId = 12
        runBlocking {
            val dataSource = localChallengeDataSource.saveChallengeDetail(challengeId, challengeDetailEntity)
            assertEquals(dataSource, Unit)
        }
    }

    @Test
    fun testFetchParticipants() {
        val challengeId = 12
        val participantEntity = ChallengeParticipantRoomEntity(
            13,
            "김재원",
            "https://testImageUrl",
            challengeId
        )
        val participantList = listOf(
            participantEntity
        )
        runBlocking {
            whenever(challengeDao.fetchParticipants(challengeId)).thenReturn(
                participantList
            )

            val dataSource = localChallengeDataSource.fetchParticipants(challengeId)
            assertEquals(dataSource, participantList.toEntity())
        }
    }

    @Test
    fun testSaveParticipants() {

    }
}