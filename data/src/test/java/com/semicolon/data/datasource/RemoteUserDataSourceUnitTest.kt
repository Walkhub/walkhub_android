package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.remote.api.UserApi
import com.semicolon.data.remote.datasource.RemoteUserDataSource
import com.semicolon.data.remote.datasource.RemoteUserDataSourceImpl
import com.semicolon.data.remote.request.users.*
import com.semicolon.data.remote.response.users.FetchCaloriesLevelResponse
import com.semicolon.data.remote.response.users.FetchMyPageResponse
import com.semicolon.data.remote.response.users.UserSignInResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class RemoteUserDataSourceUnitTest {

    private val userApi = mock<UserApi>()

    private val remoteUserDataSource: RemoteUserDataSource = RemoteUserDataSourceImpl(userApi)

    @Test
    fun testVerifyUserPhoneNumber() {
        val request = VerifyPhoneNumberSignUpRequest(
            "010-0000-0000"
        )
        runBlocking {
            val dataSourceResult = remoteUserDataSource.verifyUserPhoneNumber(request)
            assertEquals(Unit, dataSourceResult)
        }
    }

    @Test
    fun testPostUserSignUp() {
        val request = UserSignUpRequest(
            "13",
            "password",
            "김재원",
            "010-0000-0000",
            "abc",
            "대덕소프트웨어마이스터고"
        )
        runBlocking {
            val dataSourceResult = remoteUserDataSource.postUserSignUp(request)
            assertEquals(Unit, dataSourceResult)
        }
    }

    @Test
    fun testPatchDailyWalkGoal() {
        val request = PatchDailyWalkGoalRequest(3000)
        runBlocking {
            val dataSourceResult = remoteUserDataSource.patchDailyWalkGoal(request)
            assertEquals(Unit, dataSourceResult)
        }
    }

    @Test
    fun testFetchCaloriesLevelList() {
        val fetchCaloriesLevelResponse = FetchCaloriesLevelResponse(
            listOf(
                FetchCaloriesLevelResponse.CaloriesLevel(
                    1000,
                    "https://testImageUrl",
                    "커피",
                    2,
                    20,
                    "성공",
                    "R"
                )
            )
        )
        runBlocking {
            whenever(userApi.fetchCaloriesLevelList()).thenReturn(
                fetchCaloriesLevelResponse
            )

            val dataSourceResult = remoteUserDataSource.fetchCaloriesLevelList()
            assertEquals(fetchCaloriesLevelResponse, dataSourceResult)
        }
    }

    @Test
    fun testPostUserSignIn() {
        val request = UserSignInRequest(
            "13",
            "password",
            "device_token"
        )
        val response = UserSignInResponse(
            "accessToken",
            "2020-12-12-T10:12",
            "refreshToken"
        )
        runBlocking {
            whenever(userApi.userSignIn(request)).thenReturn(
                response
            )
            val dataSourceResult = remoteUserDataSource.postUserSignIn(request)
            assertEquals(response, dataSourceResult)
        }
    }

    @Test
    fun testPatchUserChangePassword() {
        val request = UserChangePasswordRequest(
            "13",
            "010-0000-0000",
            "abc",
            "newPassword"
        )
        runBlocking {
            val dataSourceResult = remoteUserDataSource.patchUserChangePassword(request)
            assertEquals(Unit, dataSourceResult)
        }
    }

    @Test
    fun testFetchMyPage() {
        val myPageResult = FetchMyPageResponse(
            13,
            "김재원",
            "https://testImageUrl",
            "대덕소프트웨어마이스터고",
            3,
            2,
            FetchMyPageResponse.TitleBadge(
                14,
                "뱃지",
                "https://testImageUrl"
            ),
            FetchMyPageResponse.Level(
                "레벨",
                "https://testImageUrl"
            )
        )

        runBlocking {
            whenever(userApi.fetchMyPage()).thenReturn(
                myPageResult
            )

            val dataSourceResult = remoteUserDataSource.fetchMyPage()
            assertEquals(myPageResult, dataSourceResult)
        }
    }

    @Test
    fun testFetchUserProfile() {

    }

    @Test
    fun testUpdateProfile() {

    }

    @Test
    fun testPatchSchool() {

    }

    @Test
    fun testFindUserAccount() {

    }

    @Test
    fun testPatchUserHealth() {

    }

    @Test
    fun testSignUpClass() {

    }

    @Test
    fun testUserReissue() {

    }
}