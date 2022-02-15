package com.semicolon.domain.usecase.user

import com.nhaarman.mockitokotlin2.mock
import com.semicolon.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class AutoLoginUseCaseUnitTest {

    private val userRepository = mock<UserRepository>()

    private val autoLoginUseCase = AutoLoginUseCase(userRepository)

    @Test
    fun testAutoLoginUseCase(){
        runBlocking {
            val useCaseResult = autoLoginUseCase.execute(Unit)
            assertEquals(Unit,useCaseResult)
        }
    }
}