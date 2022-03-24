package com.semicolon.data.remote.request.users

import com.google.gson.annotations.SerializedName

data class VerifyPhoneNumberSignUpRequest(
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("auth_code") val authCode: String
)
