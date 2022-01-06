package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName

data class UserLoginResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("expired_at") val expiredAt: Int,
    @SerializedName("refresh_token") val refreshToken: String
)