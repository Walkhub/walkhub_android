package com.semicolon.data.local.entity.badge

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.badge.FetchUserBadgesEntity

@Entity(tableName = "userBadges")
data class FetchUserBadgesRoomEntity(
    @PrimaryKey var userId: Int,
    val badgeList: List<Badge>
) {
    data class Badge(
        val badgeId: Int,
        val badgeImageUrl: String,
        val badgeName: String,
    )

    fun Badge.toEntity() =
        FetchUserBadgesEntity.Badge(
            badgeId = badgeId,
            badgeImageUrl = badgeImageUrl,
            badgeName = badgeName,
        )
}

fun FetchUserBadgesRoomEntity.toEntity() =
    FetchUserBadgesEntity(
        badgeList = badgeList.map { it.toEntity() }
    )

fun FetchUserBadgesEntity.Badge.toDbEntity() =
    FetchUserBadgesRoomEntity.Badge(
        badgeId = badgeId,
        badgeImageUrl = badgeImageUrl,
        badgeName = badgeName
    )

fun FetchUserBadgesEntity.toDbEntity(userId: Int) =
    FetchUserBadgesRoomEntity(
        userId = userId,
        badgeList = badgeList.map { it.toDbEntity() }
    )