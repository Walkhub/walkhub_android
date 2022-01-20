package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class UserReissueResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("expired_at") val expiredAt: String,
    @SerializedName("refresh_token") val refreshToken: String
)