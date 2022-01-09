package com.semicolon.data.repository

import com.semicolon.data.BaseTest
import com.semicolon.data.datasource.challenge.local.ChallengeLocalDataSource
import com.semicolon.data.datasource.challenge.remote.ChallengeRemoteDateSource
import com.semicolon.data.repository.challenge.ChallengeRepositoryImpl
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.enum.ChallengeScope
import com.semicolon.domain.repository.challenge.ChallengeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import java.time.LocalDateTime

class ChallengeRepositoryUnitTest : BaseTest(){
    @Mock
    private lateinit var challengeLocalDataSource: ChallengeLocalDataSource

    @Mock
    private lateinit var challengeRemoteDateSource: ChallengeRemoteDateSource

    private lateinit var challengeRepository: ChallengeRepository

    override fun init() {
        challengeRepository =
            ChallengeRepositoryImpl(challengeLocalDataSource, challengeRemoteDateSource)
    }

    @Test
    fun `첼린지 목록 읽어오기 성공`(): Unit = runBlocking {
        val testChallenge = ChallengeEntity(
            12,
            "challenge",
            LocalDateTime.MIN,
            LocalDateTime.MAX,
            "https://image",
            ChallengeScope.ALL
        )
        `when`(challengeRemoteDateSource.fetchChallenges())
            .thenReturn(ArrayList<ChallengeEntity>().apply {
                add(testChallenge)
            })
        `when`(challengeLocalDataSource.fetchChallenges())
            .thenReturn(ArrayList<ChallengeEntity>().apply {
                add(testChallenge)
            })

        challengeRepository.fetchChallenges().collect {
            assert(it.size == 1)
        }
    }

}