package com.semicolon.data.remote.request.users

data class UserLoginRequest(
    val account_id: String,
    val device_token: String,
    val password: String,
    val type: String
)