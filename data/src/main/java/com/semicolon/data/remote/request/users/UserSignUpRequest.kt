package com.semicolon.data.remote.request.users

import com.google.gson.annotations.SerializedName

data class UserSignUpRequest(
    @SerializedName("account_id") val accountId: String,
    @SerializedName("auth_code") val authCode: String,
    @SerializedName("name") val name: String,
    @SerializedName("password") val password: String,
    @SerializedName("phone_number") val phoneNumber: String
)