package com.semicolon.domain.usecase.user

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.semicolon.domain.param.user.PatchUserHealthParam
import com.semicolon.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PatchUserHealthUseCaseUnitTest {

    private val userRepository = mock<UserRepository>()

    private val patchUserHealthUseCase = PatchUserHealthUseCase(userRepository)

    @Test
    fun testPatchUserHealthUseCase() {
        val patchUserHealthParam = PatchUserHealthParam(176.8,60)

        runBlocking {
            val useCaseResult = patchUserHealthUseCase.execute(patchUserHealthParam)
            assertEquals(Unit,useCaseResult)

            verify(userRepository, times(1)).patchUserHealth(patchUserHealthParam)
        }
    }

}