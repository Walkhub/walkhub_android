package com.semicolon.data.remote.request.teacher

import com.google.gson.annotations.SerializedName

data class DeleteClassRequest(
    @SerializedName("grade") val grade : Int,
    @SerializedName("class") val classes : Int,
    @SerializedName("agency-code") val agencyCode : String
)
