package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.remote.api.ChallengeApi
import com.semicolon.data.remote.datasource.RemoteChallengeDateSource
import com.semicolon.data.remote.datasource.RemoteChallengeDateSourceImpl
import com.semicolon.data.remote.response.challenge.ChallengeDetailResponse
import com.semicolon.data.remote.response.challenge.ChallengeListResponse
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ChallengeRemoteDatasourceUnitTest {

    private val challengeApi = mock<ChallengeApi>()

    private val remoteChallengeDatasource: RemoteChallengeDateSource =
        RemoteChallengeDateSourceImpl(challengeApi)

    @Test
    fun testFetchChallenges() {
        val challengeList = listOf(
            ChallengeListResponse.ChallengeResponse(
                1,
                "삼천보걷기",
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
            whenever(challengeApi.getChallenges()).thenReturn(
                challengeResponse
            )

            val datasourceValue = remoteChallengeDatasource.fetchChallenges()
            val testResult = datasourceValue == challengeResponse
            assert(testResult)
        }
    }

    @Test
    fun testFetchChallengeDetail() {
        val challengeDetailResponse = ChallengeDetailResponse(
            "삼천보걷기",
            "삼천보걷기가 목표입니다",
            "수행평가 만점",
            "http://testImageUrl",
            "2022/2/12T12:14:00",
            "2022/2/17T12:14:00",
            3000,
            "WALK",
            "ALL",
            "ALL",
            false,
            100,
            ChallengeDetailResponse.ChallengeWriterResponse(
                12,
                "김재원",
                "http://testImageUrl"
            )
        )

        runBlocking {
            whenever(challengeApi.getChallengeDetail(12)).thenReturn(
                challengeDetailResponse
            )

            val challengeDataSource = remoteChallengeDatasource.fetchChallengeDetail(12)
            val testResult = challengeDataSource == challengeDetailResponse

            assert(testResult)
        }

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