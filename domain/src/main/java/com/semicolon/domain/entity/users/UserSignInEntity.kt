package com.semicolon.domain.entity.users

data class UserSignInEntity(
    val accessToken: String,
    val expiredAt: String,
    val refreshToken: String
)