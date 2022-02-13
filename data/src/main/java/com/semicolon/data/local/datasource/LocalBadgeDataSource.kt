package com.semicolon.data.local.datasource

import com.semicolon.domain.entity.badge.FetchMyBadgesEntity
import com.semicolon.domain.entity.badge.FetchNewBadgesEntity
import com.semicolon.domain.entity.badge.FetchUserBadgesEntity

interface LocalBadgeDataSource {
    suspend fun fetchUserBadges(userId: Int): FetchUserBadgesEntity
    suspend fun insertUserBadge(userId: Int, fetchUserBadgesEntity: FetchUserBadgesEntity)

    suspend fun fetchMyBadges(): FetchMyBadgesEntity
    suspend fun insertMyBadges(fetchMyBadgesEntity: FetchMyBadgesEntity)

    suspend fun fetchNewBadges(): FetchNewBadgesEntity
    suspend fun insertNewBadges(fetchNewBadgesEntity: FetchNewBadgesEntity)
}