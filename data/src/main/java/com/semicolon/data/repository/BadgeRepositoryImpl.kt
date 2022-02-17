package com.semicolon.data.repository

import com.semicolon.data.local.datasource.LocalBadgeDataSource
import com.semicolon.data.remote.datasource.RemoteBadgeDataSource
import com.semicolon.data.remote.response.badge.toEntity
import com.semicolon.data.util.OfflineCacheUtil
import com.semicolon.domain.entity.badge.FetchMyBadgesEntity
import com.semicolon.domain.entity.badge.FetchNewBadgesEntity
import com.semicolon.domain.entity.badge.FetchUserBadgesEntity
import com.semicolon.domain.repository.BadgeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BadgeRepositoryImpl @Inject constructor(
    private val remoteBadgeDataSource: RemoteBadgeDataSource,
    private val localBadgeDataSource: LocalBadgeDataSource
) : BadgeRepository {

    override suspend fun fetchUserBadges(userId: Int): Flow<FetchUserBadgesEntity> =
        OfflineCacheUtil<FetchUserBadgesEntity>()
            .remoteData { remoteBadgeDataSource.fetchUserBadges(userId) }
            .localData { localBadgeDataSource.fetchUserBadges(userId) }
            .doOnNeedRefresh { localBadgeDataSource.insertUserBadge(userId, it) }
            .createFlow()

    override suspend fun fetchMyBadges(): Flow<FetchMyBadgesEntity> =
        OfflineCacheUtil<FetchMyBadgesEntity>()
            .remoteData { remoteBadgeDataSource.fetchMyBadges() }
            .localData { localBadgeDataSource.fetchMyBadges() }
            .doOnNeedRefresh { localBadgeDataSource.insertMyBadges(it) }
            .createFlow()

    override suspend fun setBadge(badgeId: Int) {
        remoteBadgeDataSource.setBadge(badgeId)
    }

    override suspend fun fetchNewBadges(): Flow<FetchNewBadgesEntity> =
        OfflineCacheUtil<FetchNewBadgesEntity>()
            .remoteData { remoteBadgeDataSource.fetchNewBadges() }
            .localData { localBadgeDataSource.fetchNewBadges() }
            .doOnNeedRefresh { localBadgeDataSource.insertNewBadges(it) }
            .createFlow()
}