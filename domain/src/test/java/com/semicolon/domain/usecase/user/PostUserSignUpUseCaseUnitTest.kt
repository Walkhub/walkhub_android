package com.semicolon.domain.usecase.user

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.semicolon.domain.param.user.PostUserSignInParam
import com.semicolon.domain.param.user.PostUserSignUpParam
import com.semicolon.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PostUserSignUpUseCaseUnitTest {

    private val userRepository = mock<UserRepository>()

    private val postUserSignUpUseCase = PostUserSignUpUseCase(userRepository)

    private val postUserSignUpParam = mock<PostUserSignUpParam>()

    @Test
    fun testPostUserSignUpUseCase() {
        val postUserSignUpParam = PostUserSignUpParam(
            "account_id",
            "password",
            "device_token",
        "010-1234-1234",
        "auth_code",
        "school_name")

        runBlocking {
            val useCaseResult = postUserSignUpUseCase.execute(postUserSignUpParam)
            assertEquals(Unit, useCaseResult)

            verify(userRepository).postUserSignUp(postUserSignUpParam)
        }
    }
}