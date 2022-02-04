package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.users.UserOwnBadgeEntity

data class FetchOwnBadgeResponse(
    @SerializedName("badge_list") val badgeList: List<Badge>
) {
    data class Badge(
        @SerializedName("id") val id: Int,
        @SerializedName("image") val image: String,
        @SerializedName("name") val name: String
    )

    fun Badge.toEntity() =
        UserOwnBadgeEntity.Badge(
            id = id,
            image = image,
            name = name
        )
}

fun FetchOwnBadgeResponse.toEntity() =
    UserOwnBadgeEntity(
        badgeList = badgeList.map { it.toEntity() }
    )