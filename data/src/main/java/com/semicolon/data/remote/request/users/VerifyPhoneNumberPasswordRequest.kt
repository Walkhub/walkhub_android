package com.semicolon.data.remote.request.users

import com.google.gson.annotations.SerializedName

data class VerifyPhoneNumberPasswordRequest(
    @SerializedName("phone_number") val phoneNumber: String
)
