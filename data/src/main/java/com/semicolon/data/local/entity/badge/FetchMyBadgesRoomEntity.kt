package com.semicolon.data.local.entity.badge

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.badge.FetchMyBadgesEntity

@Entity(tableName = "myBadges")
data class FetchMyBadgesRoomEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val badgeList: List<Badge>
) {
    data class Badge(
        val badgeId: Int,
        val badgeImageUrl: String,
        val badgeName: String,
        val mine: Boolean,
        val condition: String
    )

    fun Badge.toEntity() =
        FetchMyBadgesEntity.Badge(
            badgeId = badgeId,
            badgeImageUrl = badgeImageUrl,
            badgeName = badgeName,
            mine = mine,
            condition = condition
        )
}

fun FetchMyBadgesRoomEntity.toEntity() =
    FetchMyBadgesEntity(
        badgeList = badgeList.map { it.toEntity() }
    )

fun FetchMyBadgesEntity.Badge.toDbEntity() =
    FetchMyBadgesRoomEntity.Badge(
        badgeId = badgeId,
        badgeImageUrl = badgeImageUrl,
        badgeName = badgeName,
        mine = mine,
        condition = condition
    )

fun FetchMyBadgesEntity.toDbEntity() =
    FetchMyBadgesRoomEntity(
        badgeList = badgeList.map { it.toDbEntity() }
    )