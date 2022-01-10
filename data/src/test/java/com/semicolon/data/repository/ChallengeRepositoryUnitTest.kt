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
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList

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
    fun `Fetch Challenges Success`(): Unit = runBlocking {
        val testChallenge = ChallengeEntity(
            12,
            "challenge",
            Date.from(Instant.MIN),
            Date.from(Instant.MAX),
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