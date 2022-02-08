package com.semicolon.data.local.datasource

import com.semicolon.data.local.dao.UserDao
import com.semicolon.data.local.entity.user.toDbEntity
import com.semicolon.data.local.entity.user.toEntity
import com.semicolon.data.local.storage.AuthDataStorage
import com.semicolon.domain.entity.users.UserMyPageEntity
import com.semicolon.domain.entity.users.UserOwnBadgeEntity
import com.semicolon.domain.entity.users.UserProfileEntity
import javax.inject.Inject

class LocalUserDataSourceImpl @Inject constructor(
    private val userDao: UserDao,
    private val authDataStorage: AuthDataStorage
) : LocalUserDataSource {

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

    override suspend fun setAccessToken(token: String) {
        authDataStorage.setAccessToken(token)
    }

    override suspend fun fetchAccessToken(): String =
        authDataStorage.fetchAccessToken()

    override suspend fun clearAccessToken() {
        authDataStorage.clearAccessToken()
    }

    override suspend fun setRefreshToken(token: String) {
        authDataStorage.setRefreshToken(token)
    }

    override suspend fun fetchRefreshToken(): String =
        authDataStorage.fetchRefreshToken()

    override suspend fun clearRefreshToken() {
        authDataStorage.clearRefreshToken()
    }

    override suspend fun setDeviceToken(deviceToken: String) {
        authDataStorage.setDeviceToken(deviceToken)
    }

    override suspend fun fetchDeviceToken(): String =
        authDataStorage.fetchDeviceToken()

    override suspend fun setId(id: String) {
        authDataStorage.setId(id)
    }

    override suspend fun fetchId(): String =
        authDataStorage.fetchId()

    override suspend fun setPw(pw: String) {
        authDataStorage.setPw(pw)
    }

    override suspend fun fetchPw(): String =
        authDataStorage.fetchPw()
}