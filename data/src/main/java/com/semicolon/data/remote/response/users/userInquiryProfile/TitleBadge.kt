package com.semicolon.data.remote.response.users.userInquiryProfile

import com.google.gson.annotations.SerializedName

data class TitleBadge(
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String,
    @SerializedName("name") val name: String
)