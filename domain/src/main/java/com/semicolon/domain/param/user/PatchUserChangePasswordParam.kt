package com.semicolon.domain.param.user

data class PatchUserChangePasswordParam(
    val accountId: String,
    val authCode: String,
    val newPassword: String,
    val phoneNumber: String
)