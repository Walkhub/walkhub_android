package com.semicolon.data.remote.request.users

data class UserChangePasswordRequest(
    val account_id: String,
    val auth_code: String,
    val new_passowrd: String,
    val phone_number: String
)