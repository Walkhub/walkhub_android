package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName

data class UserSignUpResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("expired_at") val expiredAt: String
)