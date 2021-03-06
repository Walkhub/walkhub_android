package com.semicolon.data.local.datasource

import com.semicolon.data.local.dao.UserDao
import com.semicolon.data.local.entity.user.toDbEntity
import com.semicolon.data.local.entity.user.toEntity
import com.semicolon.data.local.storage.AuthDataStorage
import com.semicolon.domain.entity.users.*
import org.threeten.bp.LocalDateTime
import javax.inject.Inject

class LocalUserDataSourceImpl @Inject constructor(
    private val userDao: UserDao,
    private val authDataStorage: AuthDataStorage
) : LocalUserDataSource {

    override suspend fun fetchUserMyPage(): UserMyPageEntity =
        userDao.fetchUserMyPage().toEntity()

    override suspend fun insertUserMyPage(userMyPageEntity: UserMyPageEntity) {
        userMyPageEntity.toDbEntity()?.let { userDao.insertUserMyPage(it) }
    }

    override suspend fun fetchUserProfile(userId: Int): UserProfileEntity =
        userDao.fetchUserProfile(userId).toEntity()

    override suspend fun insertUserProfile(userMyProfileEntity: UserProfileEntity) {
        userDao.insertUserProfile(userMyProfileEntity.toDbEntity())
    }

    override suspend fun fetchCaloriesLevelList(): FetchCaloriesLevelEntity =
        userDao.fetchCaloriesLevelList().toEntity()

    override suspend fun insertCaloriesLevelList(fetchCaloriesLevelEntity: FetchCaloriesLevelEntity) {
        userDao.insertCaloriesLevelList(fetchCaloriesLevelEntity.toDbEntity())
    }

    override suspend fun fetchDailyGoal(): FetchDailyGoalEntity =
        userDao.fetchDailyGoal().toEntity()

    override suspend fun insertDailyGoal(fetchDailyGoalEntity: FetchDailyGoalEntity) {
        userDao.insertDailyGoal(fetchDailyGoalEntity.toDbEntity())
    }

    override suspend fun fetchInfo(): FetchInfoEntity =
        userDao.fetchInfo().toEntity()

    override suspend fun insertInfo(fetchInfoEntity: FetchInfoEntity) {
        fetchInfoEntity.toDbEntity()?.let { userDao.insertInfo(it) }
    }

    override suspend fun fetchUserHealth(): FetchUserHealthEntity =
        userDao.fetchUserHealth().toEntity()

    override suspend fun insertUserHealth(fetchUserHealthEntity: FetchUserHealthEntity) {
        userDao.insertUserHealth(fetchUserHealthEntity.toDbEntity())
    }

    override suspend fun fetchAuthInfo(): FetchAuthInfoEntity =
        userDao.fetchAuthInfo().toEntity()

    override suspend fun insertAuthInfo(fetchAuthInfoEntity: FetchAuthInfoEntity) {
        userDao.insertAuthInfo(fetchAuthInfoEntity.toDbEntity())
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

    override suspend fun setExpiredAt(localDateTime: String) {
        authDataStorage.setExpiredAt(localDateTime)
    }

    override suspend fun fetchExpiredAt(): LocalDateTime =
        authDataStorage.fetchExpiredAt()

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

    override suspend fun clearId() {
        authDataStorage.clearId()
    }

    override suspend fun setPw(pw: String) {
        authDataStorage.setPw(pw)
    }

    override suspend fun fetchPw(): String =
        authDataStorage.fetchPw()

    override suspend fun clearPw() {
        authDataStorage.clearPw()
    }
}