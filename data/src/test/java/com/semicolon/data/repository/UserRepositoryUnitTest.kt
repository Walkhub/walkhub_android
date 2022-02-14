package com.semicolon.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.local.datasource.LocalUserDataSource
import com.semicolon.data.remote.datasource.RemoteImagesDataSource
import com.semicolon.data.remote.datasource.RemoteUserDataSource
import com.semicolon.data.remote.request.users.UserSignInRequest
import com.semicolon.data.remote.response.users.UserSignInResponse
import com.semicolon.domain.param.user.PatchUserChangePasswordParam
import com.semicolon.domain.param.user.PostUserSignInParam
import com.semicolon.domain.param.user.PostUserSignUpParam
import com.semicolon.domain.param.user.VerifyPhoneNumberSignUpParam
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class UserRepositoryUnitTest {

    private val localUserDataSource = mock<LocalUserDataSource>()
    private val remoteUserDataSource = mock<RemoteUserDataSource>()

    private val imageDataSource = mock<RemoteImagesDataSource>()

    private val userRepository =
        UserRepositoryImpl(imageDataSource, localUserDataSource, remoteUserDataSource)

    val accountId = "13"
    val password = "password"
    val deviceToken = "device_token"

    private val postUserSignInParam = PostUserSignInParam(
        accountId,
        password,
        deviceToken
    )
    private val userSignInRequest = UserSignInRequest(
        accountId,
        password,
        deviceToken
    )
    private val userSignInResponse = UserSignInResponse(
        accountId,
        password,
        deviceToken
    )





    @Test
    fun testVerifyUserPhoneNumber() {
        runBlocking {
            val repositoryResult =
                userRepository.verifyUserPhoneNumber(VerifyPhoneNumberSignUpParam("010-2100-2936"))
            assertEquals(Unit, repositoryResult)
        }
    }

    @Test
    fun testPostUserSignUp() {
        val request = PostUserSignUpParam(
            "13",
            "dlwodnjs0310",
            "이재원",
            "010-2100-2936",
            "abcd",
            "대덕소프트웨어마이스터고"
        )

        runBlocking {
            val repositoryResult = userRepository.postUserSignUp(request)
            assertEquals(Unit, repositoryResult)
        }
    }
    @Test
    fun testPostUserSignIn() {

        runBlocking {
            whenever(remoteUserDataSource.postUserSignIn(userSignInRequest)).thenReturn(userSignInResponse)

            val repositoryResult = userRepository.postUserSignIn(postUserSignInParam)
            assertEquals(Unit,repositoryResult)
        }
    }

}