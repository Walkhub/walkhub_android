package com.semicolon.data.remote.request.users

import com.google.gson.annotations.SerializedName

data class CheckPasswordRequest(
    @SerializedName("password") val password: String
)