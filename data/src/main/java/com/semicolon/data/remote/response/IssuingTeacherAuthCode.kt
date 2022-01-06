package com.semicolon.data.remote.response

import com.google.gson.annotations.SerializedName

data class IssuingTeacherAuthCode(
    @SerializedName("code") val code: String
)