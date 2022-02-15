package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.*
import com.semicolon.data.local.dao.BadgeDao
import com.semicolon.data.local.datasource.LocalBadgeDataSource
import com.semicolon.data.local.datasource.LocalBadgeDataSourceImpl
import com.semicolon.data.local.entity.badge.FetchUserBadgesRoomEntity
import com.semicolon.data.local.entity.badge.toDbEntity
import com.semicolon.data.local.entity.badge.toEntity
import com.semicolon.domain.entity.badge.FetchUserBadgesEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class LocalBadgeDataSourceUnitTest {

    private val badgeDao = mock<BadgeDao>()

    private val localBadgeDataSource = LocalBadgeDataSourceImpl(badgeDao)

    private val fetchUserBadgesRoomEntity = mock<FetchUserBadgesRoomEntity>()

    @Test
    fun testFetchUserBadges() {
        val userId = 1
        runBlocking {
            whenever(badgeDao.fetchUserBadges(userId)).thenReturn(fetchUserBadgesRoomEntity)

            val dataSourceResult = localBadgeDataSource.fetchUserBadges(userId)
            assertEquals(fetchUserBadgesRoomEntity.toEntity(), dataSourceResult)

            verify(badgeDao, times(1)).fetchUserBadges(userId)
        }
    }
}