package com.semicolon.data.repository

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.local.datasource.LocalUserDataSource
import com.semicolon.data.remote.datasource.RemoteImagesDataSource
import com.semicolon.data.remote.datasource.RemoteUserDataSource
import com.semicolon.data.remote.request.users.UserSignInRequest
import com.semicolon.data.remote.response.image.ImagesResponse
import com.semicolon.data.remote.response.users.UserSignInResponse
import com.semicolon.domain.entity.users.FindUserAccountEntity
import com.semicolon.domain.entity.users.UserMyPageEntity
import com.semicolon.domain.param.user.*
import com.semicolon.domain.repository.UserRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class UserRepositoryUnitTest {

    private val localUserDataSource = mock<LocalUserDataSource>()
    private val remoteUserDataSource = mock<RemoteUserDataSource>()

    private val imageDataSource = mock<RemoteImagesDataSource>()

    private val userRepository =
        UserRepositoryImpl(imageDataSource, localUserDataSource, remoteUserDataSource)

    private val accountId = "13"
    private val password = "password"
    private val deviceToken = "device_token"
    private val phoneNumber = "010-1234-1234"

    private val postUserSignInParam = PostUserSignInParam(
        accountId,
        password,
        deviceToken
    )

    private val userSignInResponse = UserSignInResponse(
        accountId,
        password,
        deviceToken
    )

    private val myPageEntity = UserMyPageEntity(
        13,
        "김재원",
        "https://testImageUrl",
        "대덕소프트웨어마이스터고",
        3,
        2,
        UserMyPageEntity.TitleBadge(
            14,
            "뱃지",
            "https://testImageUrl"
        ),
        UserMyPageEntity.Level(
            "레벨",
            "https://testImageUrl"
        )
    )

    private val upDateProfileParam = UpdateProfileParam(
        "최민준",
        File("https://testImageUrl"),
        "male"
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
            accountId,
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
            whenever(remoteUserDataSource.postUserSignIn(any())).thenReturn(userSignInResponse)

            val repositoryResult = userRepository.postUserSignIn(postUserSignInParam)
            assertEquals(Unit, repositoryResult)
        }
    }

    @Test
    fun tesPatchUserChangePassword() {

        val patchUserChangePasswordParam = PatchUserChangePasswordParam(
            accountId,
            "010-2100-2936",
            "auth_code",
            "alswns",
        )
        runBlocking {
            val repositoryResult =
                userRepository.patchUserChangePassword(patchUserChangePasswordParam)
            assertEquals(Unit, repositoryResult)
        }
    }

    @Test
    fun testFetchMyPage() {

        runBlocking {
            whenever(localUserDataSource.fetchUserMyPage()).thenReturn(myPageEntity)
            whenever(remoteUserDataSource.fetchMyPage()).thenReturn(myPageEntity)

            val repositoryResult = userRepository.fetchMyPage()
            repositoryResult.collect {
                assertEquals(myPageEntity, it)
            }
        }
    }

    @Test
    fun testUpdateProfile() {
        runBlocking {
            val imagesResponse = ImagesResponse(listOf("http://test.image"))

            runBlocking {
                whenever(imageDataSource.postImages(any())).thenReturn(imagesResponse)

                val repositoryResult = userRepository.updateProfile(upDateProfileParam)
                assertEquals(Unit, repositoryResult)
            }
        }
    }

    @Test
    fun testFindUserAccount() {

        val findUserAccountEntity = FindUserAccountEntity("account_id")
        runBlocking {
            whenever(remoteUserDataSource.findUserAccount(any())).thenReturn(
                findUserAccountEntity
            )

            userRepository.findUserAccount("010-1234-5678")
                .collect {
                    assertEquals(findUserAccountEntity, it)
                }
        }
    }


}