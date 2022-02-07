package com.semicolon.data.local.datasource

import com.semicolon.data.local.dao.UserDao
import com.semicolon.data.local.entity.user.toDbEntity
import com.semicolon.data.local.entity.user.toEntity
import com.semicolon.domain.entity.users.UserMyPageEntity
import com.semicolon.domain.entity.users.UserOwnBadgeEntity
import com.semicolon.domain.entity.users.UserProfileEntity
import javax.inject.Inject

class LocalUserDataSourceImpl @Inject constructor(
    private val userDao: UserDao
): LocalUserDataSource {
    override suspend fun fetchUserMyPage(): UserMyPageEntity =
        userDao.fetchUserMyPage().toEntity()

    override suspend fun insertUserMyPage(userMyPageEntity: UserMyPageEntity) {
        userDao.insertUserMyPage(userMyPageEntity.toDbEntity())
    }

    override suspend fun fetchUserProfile(userId: Int): UserProfileEntity =
        userDao.fetchUserProfile(userId).toEntity()

    override suspend fun insertUserProfile(id: Int, userMyProfileEntity: UserProfileEntity) {
        userDao.insertUserProfile(userMyProfileEntity.toDbEntity(id))
    }

    override suspend fun fetchUserOwnBadge(userId: Int): UserOwnBadgeEntity =
        userDao.fetchUserOwnBadge(userId).toEntity()

    override suspend fun insertUserOwnBadge(userOwnBadgeEntity: UserOwnBadgeEntity) {
        userDao.insertUserOwnBadge(userOwnBadgeEntity.toDbEntity())
    }
}