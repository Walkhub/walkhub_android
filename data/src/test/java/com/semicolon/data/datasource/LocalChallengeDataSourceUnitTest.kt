package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.local.dao.ChallengeDao
import com.semicolon.data.local.datasource.LocalChallengeDataSource
import com.semicolon.data.local.datasource.LocalChallengeDataSourceImpl
import com.semicolon.data.local.entity.challenge.*
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity
import com.semicolon.domain.enums.ChallengeGoalScope
import com.semicolon.domain.enums.ChallengeGoalType
import com.semicolon.domain.enums.ChallengeUserScope
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
        "WALK",
        13L,
        "김재원",
        "https://testImageUrl",
        13,
        listOf(ChallengeParticipantEntity(
            13L,
            "김재",
            "https://testImageUrl"
        ))
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

    private val participantRoomEntity = ChallengeParticipantRoomEntity(
        13,
        "김재원",
        "https://testImageUrl",
        12
    )

    @Test
    fun testFetchChallenges() {
        runBlocking {
            whenever(challengeDao.fetchChallenges()).thenReturn(challengeRoomList)

            val dataSourceResult = localChallengeDataSource.fetchChallenges()
            assertEquals(challengeRoomList.toEntity(), dataSourceResult)
        }
    }

    @Test
    fun testSaveChallenges() {
        runBlocking {
            val dataSourceResult =
                localChallengeDataSource.saveChallenges(challengeRoomList.toEntity())
            assertEquals(Unit, dataSourceResult)
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

            val dataSourceResult = localChallengeDataSource.fetchChallengeDetail(challengeId)
            assertEquals(challengeAndDetail.toEntity(), dataSourceResult)
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
            val dataSourceResult =
                localChallengeDataSource.saveChallengeDetail(challengeId, challengeDetailEntity)
            assertEquals(Unit, dataSourceResult)
        }
    }

    @Test
    fun testFetchParticipants() {
        val challengeId = 12
        val participantList = listOf(
            participantRoomEntity
        )
        runBlocking {
            whenever(challengeDao.fetchParticipants(challengeId)).thenReturn(
                participantList
            )

            val dataSourceResult = localChallengeDataSource.fetchParticipants(challengeId)
            assertEquals(participantList.toEntity(), dataSourceResult)
        }
    }

    @Test
    fun testSaveParticipants() {
        val challengeId = 12
        val participantList = listOf(
            participantRoomEntity.toEntity()
        )
        runBlocking {
            val dataSourceResult =
                localChallengeDataSource.saveParticipants(challengeId, participantList)
            assertEquals(Unit, dataSourceResult)
        }
    }
}