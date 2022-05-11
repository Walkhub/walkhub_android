package com.semicolon.domain.param.user

data class PatchUserChangePasswordParam(
    val accountId: String,
    val phoneNumber: String,
    val authCode: String,
    val newPassword: String
)