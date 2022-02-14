package com.semicolon.domain.repository

import com.semicolon.domain.entity.badge.FetchMyBadgesEntity
import com.semicolon.domain.entity.badge.FetchNewBadgesEntity
import com.semicolon.domain.entity.badge.FetchUserBadgesEntity
import kotlinx.coroutines.flow.Flow

interface BadgeRepository {

    suspend fun fetchUserBadges(userId: Int): Flow<FetchUserBadgesEntity>

    suspend fun fetchMyBadges(): Flow<FetchMyBadgesEntity>

    suspend fun setBadge(badgeId: Int)

    suspend fun fetchNewBadges(): Flow<FetchNewBadgesEntity>
}