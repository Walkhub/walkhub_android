package com.semicolon.data.remote.response.badge

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.badge.FetchMyBadgesEntity
import com.semicolon.domain.entity.badge.FetchUserBadgesEntity

data class FetchMyBadgesResponse(
    @SerializedName("badge_list") val badgeList: List<Badge>
) {
    data class Badge(
        @SerializedName("badge_id") val badgeId: Int,
        @SerializedName("name") val name: String,
        @SerializedName("image_url") val imageUrl: String,
        @SerializedName("is_mine") val mine: Boolean,
        @SerializedName("condition") val condition: String
    )

    fun Badge.toEntity() =
        FetchMyBadgesEntity.Badge(
            badgeId = badgeId,
            name = name,
            imageUrl = imageUrl,
            mine = mine,
            condition = condition
        )
}

fun FetchMyBadgesResponse.toEntity() =
    FetchMyBadgesEntity(
        badgeList = badgeList.map { it.toEntity() }
    )