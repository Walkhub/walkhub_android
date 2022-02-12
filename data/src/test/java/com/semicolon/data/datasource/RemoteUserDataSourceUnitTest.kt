package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.semicolon.data.remote.api.UserApi
import com.semicolon.data.remote.datasource.RemoteUserDataSourceImpl
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

    }

    @Test
    fun testPatchDailyWalkGoal() {

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