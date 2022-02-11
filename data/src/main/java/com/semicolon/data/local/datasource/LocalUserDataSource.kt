package com.semicolon.data.local.datasource

import com.semicolon.domain.entity.users.FetchCaloriesLevelEntity
import com.semicolon.domain.entity.users.UserMyPageEntity
import com.semicolon.domain.entity.users.UserProfileEntity
import org.threeten.bp.LocalDateTime

interface LocalUserDataSource {

    suspend fun fetchUserMyPage(): UserMyPageEntity
    suspend fun insertUserMyPage(userMyPageEntity: UserMyPageEntity)

    suspend fun fetchUserProfile(userId: Int): UserProfileEntity
    suspend fun insertUserProfile(userMyProfileEntity: UserProfileEntity)

    suspend fun fetchCaloriesLevelList(): FetchCaloriesLevelEntity
    suspend fun insertCaloriesLevelList(fetchCaloriesLevelEntity: FetchCaloriesLevelEntity)

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

    suspend fun setPw(pw: String)
    suspend fun fetchPw(): String
}