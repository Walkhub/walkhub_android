package com.semicolon.data.remote.response.users.inquiryownerbadges

import com.google.gson.annotations.SerializedName

data class Badge(
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String,
    @SerializedName("name") val name: String
)