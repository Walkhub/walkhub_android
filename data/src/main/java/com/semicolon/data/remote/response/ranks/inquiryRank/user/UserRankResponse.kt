package com.semicolon.data.remote.response.ranks.inquiryRank.user

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.rank.UserRankEntity

data class UserRankResponse(
    @SerializedName("rank_list") val rankList: List<UserRank>
) {
    data class UserRank(
        @SerializedName("user_id") val userId: Int,
        @SerializedName("name") val name: String,
        @SerializedName("ranking") val ranking: Int,
        @SerializedName("profile_image_url") val profileImageUrl: String,
        @SerializedName("walk_count") val walkCount: Int
    )

    fun UserRank.toEntity() =
        UserRankEntity.UserRank(
            userId = userId,
            name = name,
            ranking = ranking,
            profileImageUrl = profileImageUrl,
            walkCount = walkCount
        )
}

fun UserRankResponse.toEntity() =
    UserRankEntity(
        rankList = rankList.map { it.toEntity() }
    )