package com.semicolon.data.remote.request.users

import com.google.gson.annotations.SerializedName

data class UserChangePasswordRequest(
    @SerializedName("password") val password: String,
    @SerializedName("new_password") val newPassword: String
)