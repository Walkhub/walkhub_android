package com.semicolon.domain.entity.users

import com.google.gson.annotations.SerializedName

data class UserSignInEntity(
    val accessToken: String,
    val expiredAt: Int,
    val refreshToken: String
)