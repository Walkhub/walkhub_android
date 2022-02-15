package com.semicolon.domain.usecase.user

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.semicolon.domain.param.user.SignUpClassParam
import com.semicolon.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class SignUpClassUseCaseUnitTest {

    private val userRepository = mock<UserRepository>()

    private val signUpClassUseCase = SignUpClassUseCase(userRepository)

    private val signUpClassParam = mock<SignUpClassParam>()

    @Test
    fun testSignUpClassUseCase() {

        val signUpClassParam = SignUpClassParam(2,"2-3",19)
        runBlocking {
            val useCaseResult = signUpClassUseCase.execute(signUpClassParam)
            assertEquals(Unit,useCaseResult)

            verify(userRepository, times(1)).signUpClass(signUpClassParam)
        }
    }
}