package com.semicolon.data.remote.response.challenge

import com.google.gson.annotations.SerializedName

data class ChallengeWriterResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("profile_image_url") val profileUrl: String
)