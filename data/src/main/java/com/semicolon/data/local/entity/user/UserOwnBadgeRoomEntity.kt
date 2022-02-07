package com.semicolon.data.local.entity.user

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.users.UserOwnBadgeEntity

@Entity(tableName = "ownBadge")
data class UserOwnBadgeRoomEntity(
    @PrimaryKey (autoGenerate = true) var id: Int = 0,
    val badgeList: List<Badge>
) {
    data class Badge(
         val badgeId: Int,
        val badgeImage: String,
        val badgeName: String
    )

    fun Badge.toEntity() =
        UserOwnBadgeEntity.Badge (
            badgeId = badgeId,
            badgeImage = badgeImage,
            badgeName = badgeName
        )
}

fun UserOwnBadgeRoomEntity.toEntity() =
    UserOwnBadgeEntity(
        badgeList = badgeList.map { it.toEntity() }
    )

fun UserOwnBadgeEntity.Badge.toDbEntity() =
    UserOwnBadgeRoomEntity.Badge(
        badgeId = badgeId,
        badgeImage = badgeImage,
        badgeName = badgeName
    )

fun UserOwnBadgeEntity.toDbEntity() =
    UserOwnBadgeRoomEntity(
        badgeList = badgeList.map { it.toDbEntity() }
    )