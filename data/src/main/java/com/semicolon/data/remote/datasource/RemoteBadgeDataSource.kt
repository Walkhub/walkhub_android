package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.response.badge.FetchMyBadgesResponse
import com.semicolon.data.remote.response.badge.FetchNewBadgesResponse
import com.semicolon.data.remote.response.badge.FetchUserBadgesResponse
import com.semicolon.domain.entity.badge.FetchMyBadgesEntity
import com.semicolon.domain.entity.badge.FetchNewBadgesEntity
import com.semicolon.domain.entity.badge.FetchUserBadgesEntity

interface RemoteBadgeDataSource {

    suspend fun fetchUserBadges(userId: Int): FetchUserBadgesEntity

    suspend fun fetchMyBadges(): FetchMyBadgesEntity

    suspend fun setBadge(badgeId: Int)

    suspend fun fetchNewBadges(): FetchNewBadgesEntity
}