package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.semicolon.data.remote.api.UserApi
import com.semicolon.data.remote.datasource.RemoteUserDataSourceImpl
import com.semicolon.data.remote.request.users.PatchDailyWalkGoalRequest
import com.semicolon.data.remote.request.users.UserSignUpRequest
import com.semicolon.data.remote.request.users.VerifyPhoneNumberSignUpRequest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class RemoteUserDataSourceUnitTest {

    private val userApi = mock<UserApi>()

    private val remoteUserDataSource = RemoteUserDataSourceImpl(userApi)

    @Test
    fun testVerifyUserPhoneNumber() {
         val request = VerifyPhoneNumberSignUpRequest(
             "010-0000-0000"
         )
        runBlocking {
            val dataSourceResult = remoteUserDataSource.verifyUserPhoneNumber(request)
            assertEquals(dataSourceResult, Unit)
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
            assertEquals(dataSourceResult, Unit)
        }
    }

    @Test
    fun testPatchDailyWalkGoal() {
        val request = PatchDailyWalkGoalRequest(3000)
        runBlocking {
            val dataSourceResult = remoteUserDataSource.patchDailyWalkGoal(request)
            assertEquals(dataSourceResult, Unit)
        }
    }

    @Test
    fun testFetchCaloriesLevelList() {

    }

    @Test
    fun testPostUserSignIn() {

    }

    @Test
    fun testPatchUserChangePassword() {

    }

    @Test
    fun testFetchMyPage() {

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