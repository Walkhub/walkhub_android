package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.local.dao.ChallengeDao
import com.semicolon.data.local.datasource.LocalChallengeDataSource
import com.semicolon.data.local.datasource.LocalChallengeDataSourceImpl
import com.semicolon.data.local.entity.challenge.ChallengeAndDetail
import com.semicolon.data.local.entity.challenge.ChallengeDetailRoomEntity
import com.semicolon.data.local.entity.challenge.ChallengeRoomEntity
import com.semicolon.data.local.entity.challenge.toEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

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
        val challengeDetailRoomEntity = ChallengeDetailRoomEntity(
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
        val challengeAndDetail = ChallengeAndDetail(
            challengeRoomEntity,
            challengeDetailRoomEntity
        )
        runBlocking {
            whenever(challengeDao.fetchChallengeDetail(12)).thenReturn(
                challengeAndDetail
            )
            val dataSource = localChallengeDataSource.fetchChallengeDetail(12)
            assertEquals(dataSource, challengeAndDetail)
        }
    }

    @Test
    fun testSaveChallengeDetail() {

    }

    @Test
    fun testFetchParticipants() {

    }

    @Test
    fun testSaveParticipants() {

    }
}