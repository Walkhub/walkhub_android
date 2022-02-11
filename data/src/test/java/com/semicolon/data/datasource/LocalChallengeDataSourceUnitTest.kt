package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.semicolon.data.local.dao.ChallengeDao
import com.semicolon.data.local.datasource.LocalChallengeDataSource
import com.semicolon.data.local.datasource.LocalChallengeDataSourceImpl
import org.junit.Test

class LocalChallengeDataSourceUnitTest {

    private val challengeDao = mock<ChallengeDao>()

    private val localChallengeDataSource: LocalChallengeDataSource = LocalChallengeDataSourceImpl(challengeDao)

    @Test
    fun testFetchChallenges() {

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