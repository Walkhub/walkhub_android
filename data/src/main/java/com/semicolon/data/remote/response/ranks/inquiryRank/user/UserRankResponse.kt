package com.semicolon.data.remote.response.ranks.inquiryRank.user

import com.google.gson.annotations.SerializedName

data class UserRankResponse(
    @SerializedName("rank_list") val rankList: List<UserRank>
) {
    data class UserRank(
        @SerializedName("class_num") val classNum: Int,
        @SerializedName("grade") val grade: Int,
        @SerializedName("name") val name: String,
        @SerializedName("profile_image_url") val profileImageUrl: String,
        @SerializedName("rank") val rank: Int,
        @SerializedName("user_id") val userId: Int,
        @SerializedName("walk_count") val walkCount: Int
    )
}