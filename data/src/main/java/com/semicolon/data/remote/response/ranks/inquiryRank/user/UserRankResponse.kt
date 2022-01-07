package com.semicolon.data.remote.response.ranks.inquiryRank.user

import com.google.gson.annotations.SerializedName


data class UserRankResponse(
    @SerializedName("my_rank") val myRank: MyRank,
    @SerializedName("rank_list") val userRank: List<UserRank>
) {
    data class UserRank(
        @SerializedName("name") val name: String,
        @SerializedName("profile_image_url") val profileImageUrl: String,
        @SerializedName("user_id") val userId: Int,
        @SerializedName("walk_count") val walkCount: Int
    )

    data class MyRank(
        @SerializedName("name") val name: String,
        @SerializedName("profile_image_url") val profileImageUrl: String,
        @SerializedName("rank") val rank: Int,
        @SerializedName("user_id") val userId: Int,
        @SerializedName("walk_count") val walkCount: Int
    )
}