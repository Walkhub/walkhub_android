package com.semicolon.data.remote.response.challenge

import com.google.gson.annotations.SerializedName

data class ChallengeWriterResponse(
    val id: Long,
    val name: String,
    @SerializedName("profile_image_url") val profileUrl: String
)