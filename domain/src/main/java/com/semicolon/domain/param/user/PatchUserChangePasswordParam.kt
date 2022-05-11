package com.semicolon.domain.param.user

data class PatchUserChangePasswordParam(
    val password: String,
    val newPassword: String
)