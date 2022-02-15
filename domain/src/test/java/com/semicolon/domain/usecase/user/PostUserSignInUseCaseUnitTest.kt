package com.semicolon.domain.usecase.user

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.semicolon.domain.param.user.PatchUserHealthParam
import com.semicolon.domain.param.user.PostUserSignInParam
import com.semicolon.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PostUserSignInUseCaseUnitTest {

    private val userRepository = mock<UserRepository>()

    private val patchUserSignInUseCase = PostUserSignInUseCase(userRepository)

    private val postUserSignInParam = mock<PostUserSignInParam>()

    @Test
    fun testPostUserSignInUseCase() {
        val postUserSignInParam = PostUserSignInParam("account_id", "password", "device_token")
        runBlocking {
            val useCaseResult = patchUserSignInUseCase.execute(postUserSignInParam)
            assertEquals(Unit, useCaseResult)

            verify(userRepository).postUserSignIn(postUserSignInParam)
        }
    }
}