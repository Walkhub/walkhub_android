package com.semicolon.walkhub.ui.profile.setting.model

data class PatchUserChangePasswordData(
    val accountId: String,
    val phoneNumber: String,
    val authCode: String,
    val newPassword: String
)
