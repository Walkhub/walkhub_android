package com.semicolon.data.remote.request.users

import com.google.gson.annotations.SerializedName

data class SignUpClassRequest(
    @SerializedName("class_code") val classCode: String,
    @SerializedName("number") val number: Int
)