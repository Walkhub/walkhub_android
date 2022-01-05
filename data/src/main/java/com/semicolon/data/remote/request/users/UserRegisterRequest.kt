package com.semicolon.data.remote.request.users

data class UserRegisterRequest(
    val account_id: String,
    val auth_code: String,
    val name: String,
    val password: String,
    val phone_number: String
)