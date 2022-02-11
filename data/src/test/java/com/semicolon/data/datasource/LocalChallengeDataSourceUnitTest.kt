package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.local.dao.ChallengeDao
import com.semicolon.data.local.datasource.LocalChallengeDataSource
import com.semicolon.data.local.datasource.LocalChallengeDataSourceImpl
import com.semicolon.data.local.entity.challenge.ChallengeRoomEntity
import com.semicolon.data.local.entity.challenge.toEntity
import com.semicolon.data.remote.response.challenge.ChallengeListResponse
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.threeten.bp.LocalDateTime

class LocalChallengeDataSourceUnitTest {

    private val challengeDao = mock<ChallengeDao>()

    private val localChallengeDataSource: LocalChallengeDataSource =
        LocalChallengeDataSourceImpl(challengeDao)

    private val challengeList = listOf(
        ChallengeRoomEntity(
            1,
            "삼천보걷기",
            "2022-2-12`T`12:14:00",
            "2022-2-17`T`12:14:00",
            "https://testImageUrl",
            "ALL",
            "ALL",
            "WALK"
        )
    )

    @Test
    fun testFetchChallenges() {
        runBlocking {
            whenever(challengeDao.fetchChallenges()).thenReturn(challengeList)


            val dataSource = localChallengeDataSource.fetchChallenges()
            assertEquals(dataSource, challengeList.toEntity())
        }
    }

    @Test
    fun testSaveChallenges() {

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