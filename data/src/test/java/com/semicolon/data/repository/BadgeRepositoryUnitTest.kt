package com.semicolon.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.local.datasource.LocalBadgeDataSource
import com.semicolon.data.remote.datasource.RemoteBadgeDataSource
import com.semicolon.domain.entity.badge.FetchMyBadgesEntity
import com.semicolon.domain.entity.badge.FetchUserBadgesEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BadgeRepositoryUnitTest {

    private val remoteBadgeDataSource = mock<RemoteBadgeDataSource>()
    private val localBadgeDataSource = mock<LocalBadgeDataSource>()

    private val badgeRepository = BadgeRepositoryImpl(
        remoteBadgeDataSource, localBadgeDataSource
    )

    private val fetchUserBadgesEntity = mock<FetchUserBadgesEntity>()
    private val fetchMyBadgesEntity = mock<FetchMyBadgesEntity>()

    @Test
    fun testFetchUserBadges() {
        val userId = 1
        runBlocking {
            whenever(remoteBadgeDataSource.fetchUserBadges(userId)).thenReturn(fetchUserBadgesEntity)
            whenever(localBadgeDataSource.fetchUserBadges(userId)).thenReturn(fetchUserBadgesEntity)

            badgeRepository.fetchUserBadges(userId)
                .collect{
                    assertEquals(fetchUserBadgesEntity,it)
                }
            verify(remoteBadgeDataSource,times(1)).fetchUserBadges(userId)
            verify(localBadgeDataSource,times(1)).fetchUserBadges(userId)
        }
    }

    @Test
    fun testFetchMyBadges() {

        runBlocking {
            whenever(remoteBadgeDataSource.fetchMyBadges()).thenReturn(fetchMyBadgesEntity)
            whenever(localBadgeDataSource.fetchMyBadges()).thenReturn(fetchMyBadgesEntity)

            badgeRepository.fetchMyBadges()
                .collect{
                    assertEquals(fetchMyBadgesEntity, it)
                }
            verify(remoteBadgeDataSource,times(1)).fetchMyBadges()
            verify(localBadgeDataSource, times(1)).fetchMyBadges()
        }
    }

    @Test
    fun testSetBadge() {
        val badgeId = 1
        runBlocking {
            val repositoryResult = badgeRepository.setBadge(badgeId)
            assertEquals(Unit, repositoryResult)

            verify(remoteBadgeDataSource, times(1)).setBadge(badgeId)
        }
    }
}
