package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.users.FetchUserHealthEntity

data class FetchUserHealthResponse(
    @SerializedName("height") val height: Double,
    @SerializedName("weight") val weight: Double,
    @SerializedName("sex") val sex: String
)

fun FetchUserHealthResponse.toEntity() =
    FetchUserHealthEntity(
        height = height,
        weight = weight,
        sex = sex
    )
