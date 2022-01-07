package com.semicolon.data.remote.response.ranks.inquiryRank.user

import com.google.gson.annotations.SerializedName

data class UserRank(
    val name: String,
    @SerializedName("profile_image_url") val profileImageUrl: String,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("walk_count") val walkCount: Int
)