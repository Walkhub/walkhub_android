package com.semicolon.data.remote.response.badge

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.badge.FetchMyBadgesEntity
import com.semicolon.domain.entity.badge.FetchUserBadgesEntity

data class FetchMyBadgesResponse(
    @SerializedName("badge_list") val badgeList: List<Badge>
) {
    data class Badge(
        @SerializedName("id") val badgeId: Int,
        @SerializedName("image_url") val badgeImageUrl: String,
        @SerializedName("name") val badgeName: String,
        @SerializedName("is_mine") val mine: Boolean
    )

    fun Badge.toEntity() =
        FetchMyBadgesEntity.Badge(
            badgeId = badgeId,
            badgeImageUrl = badgeImageUrl,
            badgeName = badgeName,
            mine = mine
        )
}

fun FetchMyBadgesResponse.toEntity() =
    FetchMyBadgesEntity(
        badgeList = badgeList.map { it.toEntity() }
    )