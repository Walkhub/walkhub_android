package com.semicolon.data.datasource

import com.google.gson.annotations.SerializedName
import com.semicolon.data.BaseTest
import com.semicolon.data.datasource.challenge.local.ChallengeLocalDataSource
import com.semicolon.data.datasource.challenge.local.ChallengeLocalDataSourceImpl
import com.semicolon.data.datasource.challenge.remote.ChallengeRemoteDateSource
import com.semicolon.data.datasource.challenge.remote.ChallengeRemoteDateSourceImpl
import com.semicolon.data.local.database.challenge.ChallengeDao
import com.semicolon.data.remote.api.ChallengeApi
import com.semicolon.data.remote.response.challenge.ChallengeListResponse
import com.semicolon.data.remote.response.challenge.toEntity
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import java.time.LocalDateTime

class ChallengeDatasourceUnitTest : BaseTest() {
    @Mock
    private lateinit var challengeApi: ChallengeApi

    @Mock
    private lateinit var challengeDao: ChallengeDao

    private lateinit var challengeRemoteDataSource: ChallengeRemoteDateSource

    private lateinit var challengeLocalDataSource: ChallengeLocalDataSource

    override fun init() {
        challengeRemoteDataSource = ChallengeRemoteDateSourceImpl(challengeApi)
        challengeLocalDataSource = ChallengeLocalDataSourceImpl(challengeDao)
    }

    @Test
    fun `RemoteDatasource 챌린지 가저오기 성공`() {
        runBlocking {
            val challengeListMock = ArrayList<ChallengeListResponse.ChallengeResponse>().apply {
                add(
                    ChallengeListResponse.ChallengeResponse(
                        12,
                        "도전!",
                        LocalDateTime.MIN,
                        LocalDateTime.MAX,
                        "https://image",
                        "ALL"
                    )
                )
            }
            val challengeResponseMock = ChallengeListResponse(challengeListMock)
            `when`(challengeApi.getChallenges())
                .thenReturn(challengeResponseMock)

            val testValue = challengeRemoteDataSource.fetchChallenges()
            assert(testValue == challengeResponseMock.toEntity())
        }
    }

    @Test
    fun `LocalDatasource 챌린지 가져오기 성공`() {
        runBlocking {

        }
    }
}