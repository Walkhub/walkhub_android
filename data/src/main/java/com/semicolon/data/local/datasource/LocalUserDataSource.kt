package com.semicolon.data.local.datasource

import com.semicolon.domain.entity.users.UserMyPageEntity
import com.semicolon.domain.entity.users.UserOwnBadgeEntity
import com.semicolon.domain.entity.users.UserProfileEntity

interface LocalUserDataSource {
    suspend fun fetchUserMyPage(): UserMyPageEntity
    suspend fun insertUserMyPage(userMyPageEntity: UserMyPageEntity)

    suspend fun fetchUserProfile(userId: Int): UserProfileEntity
    suspend fun insertUserProfile(id: Int, userMyProfileEntity: UserProfileEntity)

    suspend fun fetchUserOwnBadge(userId: Int): UserOwnBadgeEntity
    suspend fun insertUserOwnBadge(userOwnBadgeEntity: UserOwnBadgeEntity)
}