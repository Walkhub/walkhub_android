package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.remote.api.ChallengeApi
import com.semicolon.data.remote.datasource.RemoteChallengeDateSource
import com.semicolon.data.remote.datasource.RemoteChallengeDateSourceImpl
import com.semicolon.data.remote.response.challenge.ChallengeDetailResponse
import com.semicolon.data.remote.response.challenge.ChallengeListResponse
import com.semicolon.data.remote.response.challenge.ChallengeParticipantListResponse
import com.semicolon.data.remote.response.challenge.toEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ChallengeRemoteDatasourceUnitTest {

    private val challengeApi = mock<ChallengeApi>()

    private val remoteChallengeDatasource: RemoteChallengeDateSource =
        RemoteChallengeDateSourceImpl(challengeApi)

    private val challengeList = listOf(
        ChallengeListResponse.ChallengeResponse(
            1,
            "삼천보걷기",
            "2022-12-12T12:12",
            "2022-12-17T12:12",
            "https://testImageUrl",
            "ALL",
            "ALL",
            "WALK"
        )
    )

    private val challengeResponse = ChallengeListResponse(challengeList)

    @Test
    fun testFetchChallenges() {
        runBlocking {
            whenever(challengeApi.getChallenges()).thenReturn(
                challengeResponse
            )
            val datasourceValue = remoteChallengeDatasource.fetchChallenges()

            assertEquals(datasourceValue, challengeResponse.toEntity())
        }
    }

    @Test
    fun testFetchChallengeDetail() {
        val challengeDetailResponse = ChallengeDetailResponse(
            "삼천보걷기",
            "삼천보걷기가 목표입니다",
            "수행평가 만점",
            "http://testImageUrl",
            "2022-12-12T12:12",
            "2022-12-17T12:12",
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
        val challengeId = 12

        runBlocking {
            whenever(challengeApi.getChallengeDetail(challengeId)).thenReturn(
                challengeDetailResponse
            )

            val challengeDataSource = remoteChallengeDatasource.fetchChallengeDetail(challengeId)
            assertEquals(challengeDataSource, challengeDetailResponse.toEntity())
        }
    }

    @Test
    fun testPostParticipate() {
        val challengeId = 12
        runBlocking {
            whenever(challengeApi.postParticipateChallenge(challengeId)).thenReturn(Unit)

            val datasourceResult = remoteChallengeDatasource.postParticipate(challengeId)
            assertEquals(datasourceResult, Unit)
        }
    }

    @Test
    fun testFetchParticipants() {
        val participantList = listOf(
            ChallengeParticipantListResponse.ChallengeParticipantResponse(
                10, "김재원", "https://testImageUrl"
            )
        )
        val participantListResponse = ChallengeParticipantListResponse(1, participantList)
        val challengeId = 12
        runBlocking {
            whenever(challengeApi.getChallengeParticipants(challengeId)).thenReturn(
                participantListResponse
            )

            val datasourceResult = remoteChallengeDatasource.fetchParticipants(challengeId)
            assertEquals(datasourceResult, participantListResponse.toEntity())
        }
    }

    @Test
    fun testFetchMyChallenges() {
        runBlocking {
            whenever(challengeApi.getMyChallenges()).thenReturn(
                challengeResponse
            )

            val datasourceResult = remoteChallengeDatasource.fetchMyChallenges()
            assertEquals(datasourceResult, challengeResponse.toEntity())
        }
    }
}