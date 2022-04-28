package com.semicolon.data.local.datasource

import com.semicolon.domain.entity.users.*
import org.threeten.bp.LocalDateTime

interface LocalUserDataSource {

    suspend fun fetchUserMyPage(): UserMyPageEntity
    suspend fun insertUserMyPage(userMyPageEntity: UserMyPageEntity)

    suspend fun fetchUserProfile(userId: Int): UserProfileEntity
    suspend fun insertUserProfile(userMyProfileEntity: UserProfileEntity)

    suspend fun fetchCaloriesLevelList(): FetchCaloriesLevelEntity
    suspend fun insertCaloriesLevelList(fetchCaloriesLevelEntity: FetchCaloriesLevelEntity)

    suspend fun fetchDailyGoal(): FetchDailyGoalEntity
    suspend fun insertDailyGoal(fetchDailyGoalEntity: FetchDailyGoalEntity)

    suspend fun fetchInfo(): FetchInfoEntity
    suspend fun insertInfo(fetchInfoEntity: FetchInfoEntity)

    suspend fun fetchUserHealth(): FetchUserHealthEntity
    suspend fun insertUserHealth(fetchUserHealthEntity: FetchUserHealthEntity)

    suspend fun fetchAuthInfo(): FetchAuthInfoEntity
    suspend fun insertAuthInfo(fetchAuthInfoEntity: FetchAuthInfoEntity)

    suspend fun setAccessToken(token: String)
    suspend fun fetchAccessToken(): String
    suspend fun clearAccessToken()

    suspend fun setRefreshToken(token: String)
    suspend fun fetchRefreshToken(): String
    suspend fun clearRefreshToken()

    suspend fun setExpiredAt(localDateTime: String)
    suspend fun fetchExpiredAt(): LocalDateTime

    suspend fun setDeviceToken(deviceToken: String)
    suspend fun fetchDeviceToken(): String

    suspend fun setId(id: String)
    suspend fun fetchId(): String
    suspend fun clearId()

    suspend fun setPw(pw: String)
    suspend fun fetchPw(): String
    suspend fun clearPw()
}