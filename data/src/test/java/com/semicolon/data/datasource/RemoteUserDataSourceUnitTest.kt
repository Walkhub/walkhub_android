package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.remote.api.UserApi
import com.semicolon.data.remote.datasource.RemoteUserDataSource
import com.semicolon.data.remote.datasource.RemoteUserDataSourceImpl
import com.semicolon.data.remote.request.users.*
import com.semicolon.data.remote.response.users.*
import com.semicolon.domain.enums.SexType
import com.semicolon.domain.param.user.VerifyPhoneNumberSignUpParam
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class RemoteUserDataSourceUnitTest {

    private val userApi = mock<UserApi>()

    private val remoteUserDataSource: RemoteUserDataSource = RemoteUserDataSourceImpl(userApi)

    @Test
    fun testVerifyUserPhoneNumber() {
        val request = VerifyPhoneNumberSignUpParam(
            "010-0000-0000",
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
            175.0,
            60,
            SexType.MALE,
            1,
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
            assertEquals(fetchCaloriesLevelResponse.toEntity(), dataSourceResult)
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
            "refreshToken",
            "authority",
            175.0,
            60.0,
            "male"
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
        val myPageResponse = FetchMyPageResponse(
            13,
            "김재원",
            "https://testImageUrl",
            1,
            "https://testImageUrl",
            "https://testImageUrl",
            2,
            3,
            10000,
            FetchMyPageResponse.TitleBadge(
                14,
                "뱃지",
                "https://testImageUrl"
            ),
            FetchMyPageResponse.Level(
                3,
                "아이스티",
                "https://testImageUrl"
            )
        )

        runBlocking {
            whenever(userApi.fetchMyPage()).thenReturn(
                myPageResponse
            )

            val dataSourceResult = remoteUserDataSource.fetchMyPage()
            assertEquals(myPageResponse.toEntity(), dataSourceResult)
        }
    }

    @Test
    fun testFetchUserProfile() {
        val userId = 12
        val userProfile = FetchUserProfileResponse(
            13,
            "김재원",
            "https://testImageUrl",
            "대덕소프트웨어마이스터고",
            "https://testImageUrl",
            3,
            2,
            10,
            FetchUserProfileResponse.TitleBadge(
                14,
                "뱃지",
                "https://testImageUrl"
            ),
            FetchUserProfileResponse.Level(
                "레벨",
                "https://testImageUrl"
            )
        )

        runBlocking {
            whenever(userApi.fetchUserProfile(userId)).thenReturn(
                userProfile
            )

            val dataSourceResult = remoteUserDataSource.fetchUserProfile(userId)
            assertEquals(userProfile.toEntity(), dataSourceResult)
        }
    }

    @Test
    fun testUpdateProfile() {
        val request = UpdateProfileRequest(
            "김재원",
            "https://testImageUrl",
            "M"
        )
        runBlocking {
            val dataSourceResult = remoteUserDataSource.updateProfile(request)
            assertEquals(Unit, dataSourceResult)
        }
    }

    @Test
    fun testPatchSchool() {
        val schoolId = 14
        runBlocking {
            val dataSourceResult = remoteUserDataSource.patchSchool(schoolId)
            assertEquals(Unit, dataSourceResult)
        }
    }

    @Test
    fun testFindUserAccount() {
        val phoneNumber = "010-0000-0000"
        val response = FindUserAccountResponse("12")
        runBlocking {
            whenever(userApi.findUserAccount(phoneNumber)).thenReturn(
                response
            )

            val dataSourceResult = remoteUserDataSource.findUserAccount(phoneNumber)
            assertEquals(response.toEntity(), dataSourceResult)
        }
    }

    @Test
    fun testPatchUserHealth() {
        val request = PatchUserHealthRequest(
            182.2,
            80,
            "male"
        )
        runBlocking {
            val dataSourceResult = remoteUserDataSource.patchUserHealth(request)
            assertEquals(Unit, dataSourceResult)
        }
    }

    @Test
    fun testSignUpClass() {
        val request = SignUpClassRequest(
            "abc",
            3202
        )
        runBlocking {
            val dataSourceResult = remoteUserDataSource.signUpClass(SignUpClassRequest("대덕소프트웨어마이스터고",1))
            assertEquals(Unit, dataSourceResult)
        }
    }

    @Test
    fun testUserReissue() {
        val refreshToken = "refreshToken"
        val response = UserReissueResponse(
            "accessToken",
            "2020-12-12T12:14"
        )
        runBlocking {
            whenever(userApi.userReissue(refreshToken)).thenReturn(
                response
            )

            val dataSourceResult = remoteUserDataSource.userReissue(refreshToken)
            assertEquals(response , dataSourceResult)
        }
    }
}