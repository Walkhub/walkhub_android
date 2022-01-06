package com.semicolon.data.remote.request.users

import com.google.gson.annotations.SerializedName

data class InputHealthRequest(
    @SerializedName("height") val height: Double,
    @SerializedName("weight") val weight: Int
)