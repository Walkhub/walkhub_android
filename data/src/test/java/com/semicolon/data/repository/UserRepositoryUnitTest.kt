package com.semicolon.data.repository

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.local.datasource.LocalUserDataSource
import com.semicolon.data.remote.datasource.RemoteImagesDataSource
import com.semicolon.data.remote.datasource.RemoteUserDataSource
import com.semicolon.data.remote.request.users.UserSignInRequest
import com.semicolon.data.remote.request.users.VerifyPhoneNumberSignUpRequest
import com.semicolon.data.remote.response.image.ImagesResponse
import com.semicolon.data.remote.response.users.UserSignInResponse
import com.semicolon.domain.entity.users.FetchCaloriesLevelEntity
import com.semicolon.domain.entity.users.FindUserAccountEntity
import com.semicolon.domain.entity.users.UserMyPageEntity
import com.semicolon.domain.entity.users.UserProfileEntity
import com.semicolon.domain.param.user.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class UserRepositoryUnitTest {

    private val localUserDataSource = mock<LocalUserDataSource>()
    private val remoteUserDataSource = mock<RemoteUserDataSource>()

    private val imageDataSource = mock<RemoteImagesDataSource>()

    private val userRepository =
        UserRepositoryImpl(imageDataSource, localUserDataSource, remoteUserDataSource)

    private val fetchCaloriesLevelEntity = mock<FetchCaloriesLevelEntity>()
    private val userProfileEntity = mock<UserProfileEntity>()
    private val userSignInResponse = mock<UserSignInResponse>()
    private val myPageEntity = mock<UserMyPageEntity>()

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
            "accountId",
            "dlwodnjs0310",
            "이재원",
            "010-2100-2936",
            176.0,
            60.0,
            "male",
            1,
            "대덕소프트웨어마이스터고"
        )

        runBlocking {
            val repositoryResult = userRepository.postUserSignUp(request)
            assertEquals(Unit, repositoryResult)
        }
    }

/*    @Test
    fun testPostUserSignIn() {
        val postUserSignInParam = PostUserSignInParam(
            "accountId",
            "password",
        )
        runBlocking {
            whenever(remoteUserDataSource.postUserSignIn(any())).thenReturn(userSignInResponse)

            val repositoryResult = userRepository.postUserSignIn(postUserSignInParam)
            assertEquals(Unit, repositoryResult)
        }
    }*/

    @Test
    fun tesPatchUserChangePassword() {
        val patchUserChangePasswordParam = PatchUserChangePasswordParam(
            "accountId",
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

            userRepository.fetchMyPage()
                .collect {
                    assertEquals(myPageEntity, it)
                }
        }
    }

    @Test
    fun testUpdateProfile() {
        val upDateProfileParam = UpdateProfileParam(
            "최민준",
            File("https://testImageUrl"),
            "male"
        )
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

    @Test
    fun testPatchUserHealth() {
        val patchUserHealthParam = PatchUserHealthParam(
            176.8,
            60,
            "male"
        )
        runBlocking {
            val repositoryResult = userRepository.patchUserHealth(patchUserHealthParam)
            assertEquals(Unit, repositoryResult)
        }
    }

    @Test
    fun testSignUpClass() {
        val signUpClassParam = SignUpClassParam(
            "2-3",
            19
        )
        runBlocking {
            val repositoryResult = userRepository.signUpClass(signUpClassParam)
            assertEquals(Unit, repositoryResult)
        }
    }

    @Test
    fun testPatchSchool() {
        runBlocking {
            val repositoryResult = userRepository.patchSchool(1)
            assertEquals(Unit, repositoryResult)
        }
    }

    @Test
    fun testAutoLogin() {
        val id = "id"
        val password = "password"
        val deviceToken = "device_token"
        runBlocking {

            whenever(localUserDataSource.fetchId()).thenReturn(id)
            whenever(localUserDataSource.fetchPw()).thenReturn(password)
            whenever(localUserDataSource.fetchDeviceToken()).thenReturn(deviceToken)

            userRepository.autoLogin()
            verify(remoteUserDataSource)
                .postUserSignIn(
                    UserSignInRequest(id, password, deviceToken)
                )
        }
    }

    @Test
    fun testPatchDailyWalkGoal() {
        val patchDailyWalkGoalParam = PatchDailyWalkGoalParam(3)
        runBlocking {
            val repositoryResult = userRepository.patchDailyWalkGoal(patchDailyWalkGoalParam)
            assertEquals(Unit, repositoryResult)
        }
    }

    @Test
    fun testFetchCaloriesLevel() {
        runBlocking {
            whenever(remoteUserDataSource.fetchCaloriesLevelList()).thenReturn(
                fetchCaloriesLevelEntity
            )
            whenever(localUserDataSource.fetchCaloriesLevelList()).thenReturn(
                fetchCaloriesLevelEntity
            )

            userRepository.fetchCaloriesLevel()
                .collect {
                    assertEquals(fetchCaloriesLevelEntity, it)
                }
        }
    }

    @Test
    fun testFetchUserProfile() {
        runBlocking {
            whenever(remoteUserDataSource.fetchUserProfile(any())).thenReturn(userProfileEntity)
            whenever(localUserDataSource.fetchUserProfile(any())).thenReturn(userProfileEntity)

            userRepository.fetchUserProfile(1)
                .collect {
                    assertEquals(userProfileEntity, it)
                }
        }
    }
}