package com.semicolon.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserRankList(
    @SerializedName("name") val name: String,
    @SerializedName("profile_image_url") val profileImageUrl: String,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("walk_count") val walkCount: Int
)