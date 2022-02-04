package com.semicolon.data.local.entity.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.users.UserOwnBadgeEntity

@Entity(tableName = "ownBadge")
data class UserOwnBadgeRoomEntity(
    @PrimaryKey (autoGenerate = true) var id: Int = 0,
    @SerializedName("badge_list") val badgeList: List<Badge>
) {
    data class Badge(
        @SerializedName("id") val id: Int,
        @SerializedName("image") val image: String,
        @SerializedName("name") val name: String
    )

    fun Badge.toEntity() =
        UserOwnBadgeEntity.Badge (
            id = id,
            image = image,
            name = name
        )
}

fun UserOwnBadgeRoomEntity.toEntity() =
    UserOwnBadgeEntity(
        badgeList = badgeList.map { it.toEntity() }
    )

fun UserOwnBadgeEntity.Badge.toDbEntity() =
    UserOwnBadgeRoomEntity.Badge(
        id = id,
        image = image,
        name = name
    )

fun UserOwnBadgeEntity.toDbEntity() =
    UserOwnBadgeRoomEntity(
        badgeList = badgeList.map { it.toDbEntity() }
    )