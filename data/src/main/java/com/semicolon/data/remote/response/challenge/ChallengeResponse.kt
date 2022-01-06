package com.semicolon.data.remote.response.challenge

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class ChallengeResponse(
    val id: Long,
    val name: String,
    @SerializedName("start_at") val startAt: LocalDateTime,
    @SerializedName("end_at") val endAt: LocalDateTime,
    @SerializedName("image_url") val imageUrl: String,
    val scope: String
)