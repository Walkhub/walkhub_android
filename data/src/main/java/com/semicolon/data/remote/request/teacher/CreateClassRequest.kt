package com.semicolon.data.remote.request.teacher

import com.google.gson.annotations.SerializedName

data class CreateClassRequest(
    @SerializedName("grade") val grade : Int,
    @SerializedName("class") val classes : Int
)