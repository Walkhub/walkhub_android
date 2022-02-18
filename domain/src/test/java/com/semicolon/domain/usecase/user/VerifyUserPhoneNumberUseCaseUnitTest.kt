package com.semicolon.domain.usecase.user

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.semicolon.domain.param.user.VerifyPhoneNumberSignUpParam
import com.semicolon.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class VerifyUserPhoneNumberUseCaseUnitTest {

    private val userRepository = mock<UserRepository>()

    private val verifyUserPhoneNumberUseCase = VerifyUserPhoneNumberUseCase(userRepository)

    private val verifyPhoneNumberSignUpParam = mock<VerifyPhoneNumberSignUpParam>()

    @Test
    fun testVerifyUserPhoneNumberUseCase() {

        runBlocking {
            val useCaseResult = verifyUserPhoneNumberUseCase.execute(verifyPhoneNumberSignUpParam)

            assertEquals(Unit, useCaseResult)
            verify(userRepository, times(1)).verifyUserPhoneNumber(verifyPhoneNumberSignUpParam)
        }
    }
}