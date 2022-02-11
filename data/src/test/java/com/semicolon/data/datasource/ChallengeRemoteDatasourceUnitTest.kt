package com.semicolon.data.datasource

import com.semicolon.data.remote.api.ChallengeApi
import com.semicolon.data.remote.datasource.RemoteChallengeDateSource
import com.semicolon.data.remote.datasource.RemoteChallengeDateSourceImpl
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class ChallengeRemoteDatasourceUnitTest {

    private lateinit var remoteChallengeDatasource: RemoteChallengeDateSource

    @Mock
    private lateinit var challengeApi: ChallengeApi

    @Before
    fun init() {
        remoteChallengeDatasource = RemoteChallengeDateSourceImpl(challengeApi)
    }

    @Test
    fun testFetchChallenges() {

    }

    @Test
    fun testFetchChallengeDetail() {

    }

    @Test
    fun testPostParticipate() {

    }

    @Test
    fun testFetchParticipants() {

    }

    @Test
    fun testFetchMyChallenges() {
        
    }
}