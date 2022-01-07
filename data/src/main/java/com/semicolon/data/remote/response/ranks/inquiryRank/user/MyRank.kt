package com.semicolon.data.remote.response.ranks.inquiryRank.user

import com.google.gson.annotations.SerializedName

data class MyRank(
    val name: String,
    @SerializedName("profile_image_url") val profileImageUrl: String,
    val rank: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("walk_count") val walkCount: Int
)