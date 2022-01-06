package com.semicolon.data.remote.response.users

data class UserLoginResponse(
    val access_token: String,
    val expired_at: Int,
    val refresh_token: String
)