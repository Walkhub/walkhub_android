package com.semicolon.domain.usecase.challenge

import com.nhaarman.mockitokotlin2.mock
import com.semicolon.domain.repository.ChallengeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PostParticipateChallengeUseCaseUnitTest {

    private val challengeRepository = mock<ChallengeRepository>()

    private val postParticipateChallengeUseCase = PostParticipateChallengeUseCase(challengeRepository)

    @Test
    fun testPostParticipateChallengeUseCase() {
        val challengeId = 12
        runBlocking {
            val useCaseResult = postParticipateChallengeUseCase.execute(challengeId)
            assertEquals(useCaseResult, Unit)
        }
    }
}