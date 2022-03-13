package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName

data class FetchUserHealthResponse(
    @SerializedName("height") val height: Double,
    @SerializedName("weight") val weight: Double,
    @SerializedName("sex") val sex: String
)
