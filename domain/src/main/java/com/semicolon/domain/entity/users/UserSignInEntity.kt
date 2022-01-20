package com.semicolon.domain.entity.users

data class UserSignInEntity(
    val accessToken: String,
    val expiredAt: Int,
    val refreshToken: String
)