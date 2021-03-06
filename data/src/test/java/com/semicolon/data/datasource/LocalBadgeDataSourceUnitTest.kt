package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.*
import com.semicolon.data.local.dao.BadgeDao
import com.semicolon.data.local.datasource.LocalBadgeDataSourceImpl
import com.semicolon.data.local.entity.badge.*
import com.semicolon.domain.entity.badge.FetchMyBadgesEntity
import com.semicolon.domain.entity.badge.FetchNewBadgesEntity
import com.semicolon.domain.entity.badge.FetchUserBadgesEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class LocalBadgeDataSourceUnitTest {

    private val badgeDao = mock<BadgeDao>()

    private val localBadgeDataSource = LocalBadgeDataSourceImpl(badgeDao)

    private val fetchUserBadgesRoomEntity = mock<FetchUserBadgesRoomEntity>()
    private val fetchUserBadgesEntity = mock<FetchUserBadgesEntity>()
    private val fetchMyBadgesEntity = mock<FetchMyBadgesEntity>()
    private val fetchMyBadgesRoomEntity = mock<FetchMyBadgesRoomEntity>()
    private val fetchNewBadgesRoomEntity = mock<FetchNewBadgesRoomEntity>()
    private val fetchNewBadgesEntity = mock<FetchNewBadgesEntity>()

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

    @Test
    fun testInsertUserBadge() {
        val userId = 1
        runBlocking {
            val dataSourceResult = localBadgeDataSource.insertUserBadge(userId, fetchUserBadgesEntity)
            assertEquals(Unit, dataSourceResult)

            verify(badgeDao, times(1)).insertUserBadge(fetchUserBadgesEntity.toDbEntity(userId))
        }
    }

    @Test
    fun testFetchMyBadges() {
        runBlocking {
            whenever(badgeDao.fetchMyBadges()).thenReturn(fetchMyBadgesRoomEntity)

            val dataSourceResult = localBadgeDataSource.fetchMyBadges()
            assertEquals(fetchMyBadgesRoomEntity.toEntity(), dataSourceResult)

            verify(badgeDao, times(1)).fetchMyBadges()
        }
    }

    @Test
    fun testInsertMyBadges() {
        runBlocking {
            val dataSourceResult = localBadgeDataSource.insertMyBadges(fetchMyBadgesEntity)
            assertEquals(Unit, dataSourceResult)

            verify(badgeDao, times(1)).insertMyBadges(fetchMyBadgesEntity.toDbEntity())
        }
    }

    @Test
    fun testFetchNewBadges() {
        runBlocking {
            whenever(badgeDao.fetchNewBadges()).thenReturn(fetchNewBadgesRoomEntity)

            val dataSourceResult = localBadgeDataSource.fetchNewBadges()
            assertEquals(fetchNewBadgesRoomEntity.toEntity(), dataSourceResult)

            verify(badgeDao, times(1)).fetchNewBadges()
        }
    }

    @Test
    fun testInsertNewBadges() {
        runBlocking {
            val dataSourceResult = localBadgeDataSource.insertNewBadges(fetchNewBadgesEntity)
            assertEquals(Unit,dataSourceResult)

            verify(badgeDao).insertNewBadges(fetchNewBadgesEntity.toDbEntity())
        }
    }
}