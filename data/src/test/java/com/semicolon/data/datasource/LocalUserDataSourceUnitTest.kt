package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.local.dao.UserDao
import com.semicolon.data.local.datasource.LocalUserDataSourceImpl
import com.semicolon.data.local.entity.user.toDbEntity
import com.semicolon.data.local.storage.AuthDataStorage
import com.semicolon.domain.entity.users.UserMyPageEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class LocalUserDataSourceUnitTest {

    private val userDao = mock<UserDao>()

    private val authDataStorage = mock<AuthDataStorage>()

    private val localUserDataSource = LocalUserDataSourceImpl(userDao, authDataStorage)

    @Test
    fun testFetchUserMyPage() {
        val myPageEntity = UserMyPageEntity(
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

        runBlocking {
            whenever(userDao.fetchUserMyPage()).thenReturn(
                myPageEntity.toDbEntity()
            )

            val dataSourceResult = localUserDataSource.fetchUserMyPage()
            assertEquals(dataSourceResult, myPageEntity)
        }
    }
}