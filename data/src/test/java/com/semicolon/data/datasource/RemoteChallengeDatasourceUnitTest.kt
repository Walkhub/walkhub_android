package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.remote.api.ChallengeApi
import com.semicolon.data.remote.datasource.RemoteChallengeDateSource
import com.semicolon.data.remote.datasource.RemoteChallengeDateSourceImpl
import com.semicolon.data.remote.response.challenge.*
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class RemoteChallengeDatasourceUnitTest {

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
            3000,
            "ALL",
            writer = ChallengeListResponse.User(
                13,
                "JaeWon",
                "https://testImageUrl"
            ),
            award = "award",
            participantCount = 13,
            participantList = listOf(
                ChallengeListResponse.User(
                    14,
                    "JaeWon",
                    "https://testImageUrl"
                )
            ),
            goalScope = "WALK"
        )
    )

    private val myChallengeList = listOf(
        MyChallengeResponse.ChallengeResponse(
            1,
            "삼천보걷기",
            "2022-12-12T12:12",
            "2022-12-17T12:12",
            "https://testImageUrl",
            3000,
            "ALL",
            "WALK",
            MyChallengeResponse.User(
                13,
                "김재원",
                "https://testImageUrl"
            ),
            3000
        )
    )

    private val challengeResponse = ChallengeListResponse(challengeList)
    private val myChallengeResponse = MyChallengeResponse(myChallengeList)

    @Test
    fun testFetchChallenges() {
        runBlocking {
            whenever(challengeApi.getChallenges()).thenReturn(
                challengeResponse
            )
            val datasourceValue = remoteChallengeDatasource.fetchChallenges()
            assertEquals(challengeResponse.toEntity(), datasourceValue)
        }
    }

    @Test
    fun testFetchChallengeDetail() {
        val challengeDetailResponse = ChallengeDetailResponse(
            "삼천보걷기",
            "삼천보걷기가 목표입니다",
            "수행평가 만점",
            1000,
            "Walk",
            "2022-12-12T12:12",
            "2022-12-17T12:12",
            "http://testImageUrl",
            "2022-12-17T12:12",
            "2022-12-17T12:12",
            100,
            10,
            ChallengeDetailResponse.ChallengeWriterResponse(
                12,
                "김재원",
                "http://testImageUrl"
            ),
            false,
            true,
            100,
            listOf(ChallengeDetailResponse.ParticipantList(1,"",""))
        )
        val challengeId = 12

        runBlocking {
            whenever(challengeApi.getChallengeDetail(challengeId)).thenReturn(
                challengeDetailResponse
            )

            val challengeDataSource = remoteChallengeDatasource.fetchChallengeDetail(challengeId)
            assertEquals(challengeDetailResponse.toEntity(), challengeDataSource)
        }
    }

    @Test
    fun testPostParticipate() {
        val challengeId = 12
        runBlocking {
            whenever(challengeApi.postParticipateChallenge(challengeId)).thenReturn(Unit)

            val datasourceResult = remoteChallengeDatasource.postParticipate(challengeId)
            assertEquals(Unit, datasourceResult)
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
            assertEquals(participantListResponse.toEntity(), datasourceResult)
        }
    }

    @Test
    fun testFetchMyChallenges() {
        runBlocking {
            whenever(challengeApi.getMyChallenges()).thenReturn(
                myChallengeResponse
            )

            val datasourceResult = remoteChallengeDatasource.fetchMyChallenges()
            assertEquals(myChallengeResponse.toEntity(), datasourceResult)
        }
    }
}