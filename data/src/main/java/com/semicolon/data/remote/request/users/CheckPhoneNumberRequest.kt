package com.semicolon.data.remote.request.users

data class CheckPhoneNumberRequest(
    val phone_number: String,
    val auth_code: String
)
