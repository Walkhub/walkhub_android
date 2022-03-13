package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName

data class FetchAuthInfoResponse(
    @SerializedName("account_id") val accountId: String,
    @SerializedName("phone_number") val phoneNumber: String
)