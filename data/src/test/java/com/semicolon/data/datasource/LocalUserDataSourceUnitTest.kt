package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.local.dao.UserDao
import com.semicolon.data.local.datasource.LocalUserDataSourceImpl
import com.semicolon.data.local.entity.user.toDbEntity
import com.semicolon.data.local.storage.AuthDataStorage
import com.semicolon.domain.entity.users.UserMyPageEntity
import com.semicolon.domain.entity.users.UserProfileEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class LocalUserDataSourceUnitTest {

    private val userDao = mock<UserDao>()

    private val authDataStorage = mock<AuthDataStorage>()

    private val localUserDataSource = LocalUserDataSourceImpl(userDao, authDataStorage)

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

    private val userProfileEntity = UserProfileEntity(
        13,
        "김재원",
        "https://testImageUrl",
        "대덕소프트웨어마이스터고",
        3,
        2,
        UserProfileEntity.TitleBadge(
            14,
            "뱃지",
            "https://testImageUrl"
        ),
        UserProfileEntity.Level(
            "레벨",
            "https://testImageUrl"
        )
    )

    @Test
    fun testFetchUserMyPage() {
        runBlocking {
            whenever(userDao.fetchUserMyPage()).thenReturn(
                myPageEntity.toDbEntity()
            )

            val dataSourceResult = localUserDataSource.fetchUserMyPage()
            assertEquals(dataSourceResult, myPageEntity)
        }
    }

    @Test
    fun testInsertUserMyPage() {
        runBlocking {
            val dataSourceResult = localUserDataSource.insertUserMyPage(myPageEntity)
            assertEquals(dataSourceResult, Unit)
        }
    }

    @Test
    fun testFetchUserProfile() {
        val userId = 12
        runBlocking {
            whenever(userDao.fetchUserProfile(userId)).thenReturn(
                userProfileEntity.toDbEntity()
            )

            val dataSourceResult = localUserDataSource.fetchUserProfile(userId)
            assertEquals(dataSourceResult, userProfileEntity)
        }
    }

    @Test
    fun testInsertUserProfile() {
        runBlocking {
            val dataSourceResult = localUserDataSource.insertUserMyPage(myPageEntity)
            assertEquals(dataSourceResult, Unit)
        }
    }

    @Test
    fun testFetchCaloriesLevelList() {

    }

    @Test
    fun testInsertCaloriesLevelList() {

    }

    @Test
    fun testSetAccessToken() {

    }

    @Test
    fun testFetchAccessToken() {

    }

    @Test
    fun testClearAccessToken() {

    }

    @Test
    fun testSetRefreshToken() {

    }

    @Test
    fun testFetchRefreshToken() {

    }

    @Test
    fun testClearRefreshToken() {

    }

    @Test
    fun testSetExpiredAt() {

    }

    @Test
    fun testFetchExpiredAt(){

    }

    @Test
    fun testSetDeviceToken() {

    }

    @Test
    fun testFetchDeviceToken() {

    }

    @Test
    fun testSetId() {

    }

    @Test
    fun testFetchId() {

    }

    @Test
    fun testSetPw() {

    }

    @Test
    fun testFetchPw() {

    }
}