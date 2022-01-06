package com.semicolon.data.remote.request.users

import com.google.gson.annotations.SerializedName

data class UpdateProfileRequest(
    @SerializedName("birthday") val birthday: Int,
    @SerializedName("name") val name: String,
    @SerializedName("profile_url") val profileUrl: String,
    @SerializedName("sex") val sex: String
)