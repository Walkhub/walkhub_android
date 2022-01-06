package com.semicolon.data.remote.response.ranks.search.user

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("name") val name: String,
    @SerializedName("profile_image_url") val profileImageUrl: String,
    @SerializedName("rank") val rank: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("walk_count") val walkCount: Int
)