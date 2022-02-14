package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.users.UserOwnBadgeEntity

data class FetchOwnBadgeResponse(
    @SerializedName("badge_list") val badgeList: List<Badge>
) {
    data class Badge(
        @SerializedName("id") val badgeId: Int,
        @SerializedName("image") val badgeImage: String,
        @SerializedName("name") val badgeName: String
    )

    fun Badge.toEntity() =
        UserOwnBadgeEntity.Badge(
            badgeId = badgeId,
            badgeImage = badgeImage,
            badgeName = badgeName
        )
}

fun FetchOwnBadgeResponse.toEntity() =
    UserOwnBadgeEntity(
        badgeList = badgeList.map { it.toEntity() }
    )