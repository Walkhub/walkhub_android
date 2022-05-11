package com.semicolon.data.remote.request.users

import com.google.gson.annotations.SerializedName

data class UserChangePasswordRequest(
    @SerializedName("account_id") val accountId: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("auth_code") val authCode: String,
    @SerializedName("new_password") val newPassword: String
)