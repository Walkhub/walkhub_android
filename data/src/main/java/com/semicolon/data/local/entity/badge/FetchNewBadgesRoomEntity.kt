package com.semicolon.data.local.entity.badge

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.badge.FetchNewBadgesEntity

@Entity(tableName = "newBadges")
data class FetchNewBadgesRoomEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val badgeList: List<Badge>
) {
    data class Badge(
        val badgeId: Int,
        val badgeImageUrl: String,
        val badgeName: String,
    )

    fun Badge.toEntity() =
        FetchNewBadgesEntity.Badge(
            badgeId = badgeId,
            badgeImageUrl = badgeImageUrl,
            badgeName = badgeName,
        )
}

fun FetchNewBadgesRoomEntity.toEntity() =
    FetchNewBadgesEntity(
        badgeList = badgeList.map { it.toEntity() }
    )

fun FetchNewBadgesEntity.Badge.toDbEntity() =
    FetchNewBadgesRoomEntity.Badge(
        badgeId = badgeId,
        badgeImageUrl = badgeImageUrl,
        badgeName = badgeName
    )

fun FetchNewBadgesEntity.toDbEntity() =
    FetchNewBadgesRoomEntity(
        badgeList = badgeList.map { it.toDbEntity() }
    )