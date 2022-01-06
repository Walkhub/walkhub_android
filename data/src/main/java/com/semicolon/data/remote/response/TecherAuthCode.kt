package com.semicolon.data.remote.response

import com.google.gson.annotations.SerializedName

data class TecherAuthCode(
    @SerializedName("code") val code: String
)