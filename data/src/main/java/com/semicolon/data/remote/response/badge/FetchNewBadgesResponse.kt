package com.semicolon.data.remote.response.badge

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.badge.FetchNewBadgesEntity
import com.semicolon.domain.entity.badge.FetchUserBadgesEntity

data class FetchNewBadgesResponse(
    @SerializedName("badge_list") val badgeList: List<Badge>
) {
    data class Badge(
        @SerializedName("badge_id") val badgeId: Int,
        @SerializedName("name") val badgeName: String,
        @SerializedName("image_url") val badgeImageUrl: String
    )

    fun Badge.toEntity() =
        FetchNewBadgesEntity.Badge(
            badgeId = badgeId,
            badgeImageUrl = badgeImageUrl,
            badgeName = badgeName
        )
}

fun FetchNewBadgesResponse.toEntity() =
    FetchNewBadgesEntity(
        badgeList = badgeList.map { it.toEntity() }
    )