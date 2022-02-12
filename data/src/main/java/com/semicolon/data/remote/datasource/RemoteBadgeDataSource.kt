package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.response.badge.FetchMyBadgesResponse
import com.semicolon.data.remote.response.badge.FetchNewBadgesResponse
import com.semicolon.data.remote.response.badge.FetchUserBadgesResponse

interface RemoteBadgeDataSource {

    suspend fun fetchUserBadges(userId: Int): FetchUserBadgesResponse

    suspend fun fetchMyBadges(): FetchMyBadgesResponse

    suspend fun setBadge(badgeId: Int)

    suspend fun fetchNewBadges(): FetchNewBadgesResponse
}