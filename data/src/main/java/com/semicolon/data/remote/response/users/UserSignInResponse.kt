package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.users.UserSignInEntity

data class UserSignInResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("expired_at") val expiredAt: String,
    @SerializedName("refresh_token") val refreshToken: String
)

fun UserSignInResponse.toEntity() =
    UserSignInEntity(
        accessToken = accessToken,
        expiredAt = expiredAt,
        refreshToken = refreshToken
    )