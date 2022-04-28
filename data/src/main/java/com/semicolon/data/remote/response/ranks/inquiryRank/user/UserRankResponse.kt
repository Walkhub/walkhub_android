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
        @SerializedName("profile_image_url") val profileImageUrl: String?,
        @SerializedName("walk_count") val walkCount: Int
    )

    fun UserRank.toEntity() =
        profileImageUrl?.let {
            UserRankEntity.UserRank(
                userId = userId,
                name = name,
                ranking = ranking,
                profileImageUrl = it,
                walkCount = walkCount
            )
        }
}

fun UserRankResponse.toEntity() =
    rankList.map { it.toEntity() }?.let {
        UserRankEntity(
            rankList = it as List<UserRankEntity.UserRank>
        )
    }