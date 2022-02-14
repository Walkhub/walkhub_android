package com.semicolon.data.local.datasource

import com.semicolon.data.local.dao.BadgeDao
import com.semicolon.data.local.entity.badge.toDbEntity
import com.semicolon.data.local.entity.badge.toEntity
import com.semicolon.domain.entity.badge.FetchMyBadgesEntity
import com.semicolon.domain.entity.badge.FetchNewBadgesEntity
import com.semicolon.domain.entity.badge.FetchUserBadgesEntity
import javax.inject.Inject

class LocalBadgeDataSourceImpl @Inject constructor(
    private val badgeDao: BadgeDao
) : LocalBadgeDataSource {

    override suspend fun fetchUserBadges(userId: Int): FetchUserBadgesEntity =
        badgeDao.fetchUserBadges(userId).toEntity()

    override suspend fun insertUserBadge(
        userId: Int,
        fetchUserBadgesEntity: FetchUserBadgesEntity
    ) {
        badgeDao.insertUserBadge(fetchUserBadgesEntity.toDbEntity(userId))
    }

    override suspend fun fetchMyBadges(): FetchMyBadgesEntity =
        badgeDao.fetchMyBadges().toEntity()

    override suspend fun insertMyBadges(fetchMyBadgesEntity: FetchMyBadgesEntity) {
        badgeDao.insertMyBadges(fetchMyBadgesEntity.toDbEntity())
    }

    override suspend fun fetchNewBadges(): FetchNewBadgesEntity =
        badgeDao.fetchNewBadges().toEntity()

    override suspend fun insertNewBadges(fetchNewBadgesEntity: FetchNewBadgesEntity) {
        badgeDao.insertNewBadges(fetchNewBadgesEntity.toDbEntity())
    }
}