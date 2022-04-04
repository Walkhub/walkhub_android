package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.local.dao.UserDao
import com.semicolon.data.local.datasource.LocalUserDataSourceImpl
import com.semicolon.data.local.entity.user.toDbEntity
import com.semicolon.data.local.storage.AuthDataStorage
import com.semicolon.domain.entity.users.FetchCaloriesLevelEntity
import com.semicolon.domain.entity.users.UserMyPageEntity
import com.semicolon.domain.entity.users.UserProfileEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.threeten.bp.LocalDateTime

class LocalUserDataSourceUnitTest {

    private val userDao = mock<UserDao>()

    private val authDataStorage = mock<AuthDataStorage>()

    private val localUserDataSource = LocalUserDataSourceImpl(userDao, authDataStorage)

    private val myPageEntity = UserMyPageEntity(
        13,
        "김재원",
        "https://testImageUrl",
        1,
        "대덕소프트웨어마이스터고",
        "asd",
        1,
        1,
        1,
        UserMyPageEntity.TitleBadge(
            3,
            "gold",
            "https://testImageUrl"
        )
        ,
        UserMyPageEntity.Level(
            3,
            "아이스티",
            "https://testImageUrl"
        )
    )

    private val userProfileEntity = UserProfileEntity(
        13,
        "김재원",
        "https://testImageUrl",
        "대덕소프트웨어마이스터고",
        "https://testImageUrl",
        2,
        1,
        1000,
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

    private val fetchCaloriesLevelEntity = FetchCaloriesLevelEntity(
        listOf(
            FetchCaloriesLevelEntity.CaloriesLevel(
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

    @Test
    fun testFetchUserMyPage() {
        runBlocking {
            whenever(userDao.fetchUserMyPage()).thenReturn(
                myPageEntity.toDbEntity()
            )

            val dataSourceResult = localUserDataSource.fetchUserMyPage()
            assertEquals(myPageEntity, dataSourceResult)
        }
    }

    @Test
    fun testInsertUserMyPage() {
        runBlocking {
            val dataSourceResult = localUserDataSource.insertUserMyPage(myPageEntity)
            assertEquals(Unit, dataSourceResult)
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
            assertEquals(userProfileEntity, dataSourceResult)
        }
    }

    @Test
    fun testInsertUserProfile() {
        runBlocking {
            val dataSourceResult = localUserDataSource.insertUserMyPage(myPageEntity)
            assertEquals(Unit, dataSourceResult)
        }
    }

    @Test
    fun testFetchCaloriesLevelList() {
        runBlocking {
            whenever(userDao.fetchCaloriesLevelList()).thenReturn(
                fetchCaloriesLevelEntity.toDbEntity()
            )

            val dataSourceResult = localUserDataSource.fetchCaloriesLevelList()
            assertEquals(fetchCaloriesLevelEntity, dataSourceResult)
        }
    }

    @Test
    fun testInsertCaloriesLevelList() {
        runBlocking {
            val dataSourceResult = localUserDataSource.insertCaloriesLevelList(
                fetchCaloriesLevelEntity
            )
            assertEquals(Unit, dataSourceResult)
        }
    }

    @Test
    fun testSetAccessToken() {
        runBlocking {
            val dataSourceResult = localUserDataSource.setAccessToken("access_token")
            assertEquals(Unit, dataSourceResult)
        }
    }

    @Test
    fun testFetchAccessToken() {
        val accessToken = "access_token"
        runBlocking {
            whenever(authDataStorage.fetchAccessToken()).thenReturn(
                accessToken
            )

            val dataSourceResult = localUserDataSource.fetchAccessToken()
            assertEquals(accessToken, dataSourceResult)
        }
    }

    @Test
    fun testClearAccessToken() {
        runBlocking {
            val dataSourceResult = localUserDataSource.clearAccessToken()
            assertEquals(Unit, dataSourceResult)
        }
    }

    @Test
    fun testSetRefreshToken() {
        val refreshToken = "refresh_token"
        runBlocking {
            val dataSourceResult = localUserDataSource.setRefreshToken(refreshToken)
            assertEquals(Unit, dataSourceResult)
        }
    }

    @Test
    fun testFetchRefreshToken() {
        val refreshToken = "refresh_token"
        runBlocking {
            whenever(authDataStorage.fetchRefreshToken()).thenReturn(
                refreshToken
            )

            val dataSourceResult = localUserDataSource.fetchRefreshToken()
            assertEquals(refreshToken, dataSourceResult)
        }
    }

    @Test
    fun testClearRefreshToken() {
        runBlocking {
            val dataSourceResult = localUserDataSource.clearRefreshToken()
            assertEquals(Unit, dataSourceResult)
        }
    }

    @Test
    fun testSetExpiredAt() {
        runBlocking {
            val dataSourceResult = localUserDataSource.setExpiredAt("2020-12-2T12:12")
            assertEquals(Unit, dataSourceResult)
        }
    }

    @Test
    fun testFetchExpiredAt() {
        val expiredAt = LocalDateTime.MAX
        runBlocking {
            whenever(authDataStorage.fetchExpiredAt()).thenReturn(
                expiredAt
            )

            val dataSourceResult = localUserDataSource.fetchExpiredAt()
            assertEquals(expiredAt, dataSourceResult)
        }
    }

    @Test
    fun testSetDeviceToken() {
        val deviceToken = "device_token"
        runBlocking {
            val dataSourceResult = localUserDataSource.setDeviceToken(deviceToken)
            assertEquals(Unit, dataSourceResult)
        }
    }

    @Test
    fun testFetchDeviceToken() {
        val deviceToken = "device_token"
        runBlocking {
            whenever(authDataStorage.fetchDeviceToken()).thenReturn(
                deviceToken
            )

            val dataSourceResult = localUserDataSource.fetchDeviceToken()
            assertEquals(deviceToken, dataSourceResult)
        }
    }

    @Test
    fun testSetId() {
        val id = "id"
        runBlocking {
            val dataSourceResult = localUserDataSource.setId(id)
            assertEquals(Unit, dataSourceResult)
        }
    }

    @Test
    fun testFetchId() {
        val id = "id"
        runBlocking {
            whenever(authDataStorage.fetchId()).thenReturn(
                id
            )

            val dataSourceResult = localUserDataSource.fetchId()
            assertEquals(id, dataSourceResult)
        }
    }

    @Test
    fun testSetPw() {
        val password = "password"
        runBlocking {
            val dataSourceResult = localUserDataSource.setPw(password)
            assertEquals(Unit, dataSourceResult)
        }
    }

    @Test
    fun testFetchPw() {
        val password = "password"
        runBlocking {
            whenever(authDataStorage.fetchPw()).thenReturn(
                password
            )

            val dataSourceResult = localUserDataSource.fetchPw()
            assertEquals(password, dataSourceResult)
        }
    }
}