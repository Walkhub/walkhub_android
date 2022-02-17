package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.remote.api.BadgeApi
import com.semicolon.data.remote.datasource.RemoteBadgeDataSource
import com.semicolon.data.remote.datasource.RemoteBadgeDataSourceImpl
import com.semicolon.data.remote.response.badge.FetchMyBadgesResponse
import com.semicolon.data.remote.response.badge.FetchNewBadgesResponse
import com.semicolon.data.remote.response.badge.FetchUserBadgesResponse
import com.semicolon.data.remote.response.badge.toEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class RemoteBadgeDataSourceUnitTest {

    private val badgeApi = mock<BadgeApi>()

    private val remoteBadgeDatasource: RemoteBadgeDataSource =
        RemoteBadgeDataSourceImpl(badgeApi)

    private val fetchUserBadgesResponse = mock<FetchUserBadgesResponse>()
    private val fetchMyBadgesResponse = mock<FetchMyBadgesResponse>()
    private val fetchNewBadgesResponse = mock<FetchNewBadgesResponse>()

    @Test
    fun testFetchUserBadges() {
        val userId = 1
        runBlocking {
            whenever(badgeApi.fetchUserBadges(userId)).thenReturn(fetchUserBadgesResponse)
            val dataSourceResult = remoteBadgeDatasource.fetchUserBadges(userId)

            assertEquals(fetchUserBadgesResponse.toEntity(), dataSourceResult)

            verify(badgeApi, times(1)).fetchUserBadges(userId)
        }
    }

    @Test
    fun testFetchMyBadges() {
        runBlocking {
            whenever(badgeApi.fetchMyBadges()).thenReturn(fetchMyBadgesResponse)
            val dataSourceResult = remoteBadgeDatasource.fetchMyBadges()

            assertEquals(fetchMyBadgesResponse.toEntity(), dataSourceResult)

            verify(badgeApi, times(1)).fetchMyBadges()
        }
    }



}