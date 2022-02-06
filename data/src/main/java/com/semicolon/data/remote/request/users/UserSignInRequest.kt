package com.semicolon.data.remote.request.users

import com.google.gson.annotations.SerializedName

data class UserSignInRequest(
    @SerializedName("account_id") val accountId: String,
    @SerializedName("password") val password: String,
    @SerializedName("device_token") val deviceToken: String
)