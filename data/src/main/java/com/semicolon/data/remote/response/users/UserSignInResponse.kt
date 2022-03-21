package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName

data class UserSignInResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("expired_at") val expiredAt: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("authority") val authority: String,
    @SerializedName("height") val height: Double,
    @SerializedName("weight") val weight: Double,
    @SerializedName("sex") val sex: String
)