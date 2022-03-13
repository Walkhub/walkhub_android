package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName

data class FetchInfoResponse(
    @SerializedName("name") val name: String,
    @SerializedName("profile_image_url") val profileImageUrl: String,
    @SerializedName("school_name") val schoolName: String,
    @SerializedName("grade") val grade: Int,
    @SerializedName("class_num") val classNum: Int
)
