package com.semicolon.data.datasource

import com.semicolon.data.remote.api.ChallengeApi
import com.semicolon.data.remote.datasource.RemoteChallengeDateSource
import com.semicolon.data.remote.datasource.RemoteChallengeDateSourceImpl
import com.semicolon.data.remote.response.challenge.ChallengeListResponse
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class ChallengeRemoteDatasourceUnitTest {

    @Mock
    private lateinit var challengeApi: ChallengeApi

    private lateinit var remoteChallengeDatasource: RemoteChallengeDateSource

    @Before
    fun init() {
        remoteChallengeDatasource = RemoteChallengeDateSourceImpl(challengeApi)
    }

    @Test
    fun testFetchChallenges() {
        val challengeList = listOf(
            ChallengeListResponse.ChallengeResponse(
                1,
                "김재원",
                "2022/2/12T12:14:00",
                "2022/2/17T12:14:00",
                "https://testImageUrl",
                "ALL",
                "ALL",
                "WALK"
            )
        )

        val challengeResponse = ChallengeListResponse(challengeList)

        runBlocking {
            `when`(challengeApi.getChallenges()).thenReturn(
                challengeResponse
            )

            val datasourceValue = remoteChallengeDatasource.fetchChallenges()
            val testResult = datasourceValue == challengeResponse
            assert(testResult)
        }
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