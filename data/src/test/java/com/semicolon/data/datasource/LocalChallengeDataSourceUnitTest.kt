package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.local.dao.ChallengeDao
import com.semicolon.data.local.datasource.LocalChallengeDataSource
import com.semicolon.data.local.datasource.LocalChallengeDataSourceImpl
import com.semicolon.data.local.entity.challenge.ChallengeRoomEntity
import com.semicolon.data.local.entity.challenge.toEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class LocalChallengeDataSourceUnitTest {

    private val challengeDao = mock<ChallengeDao>()

    private val localChallengeDataSource: LocalChallengeDataSource =
        LocalChallengeDataSourceImpl(challengeDao)

    private val challengeRoomList = listOf(
        ChallengeRoomEntity(
            1,
            "삼천보걷기",
            "2022-12-12T12:12",
            "2022-12-12T12:12",
            "https://testImageUrl",
            "ALL",
            "ALL",
            "WALK"
        )
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