package com.semicolon.data.remote.response.challenge

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class ChallengeDetailResponse(
    val name: String,
    val content: String,
    val goal: Int,
    val award: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("start_at") val startAt: LocalDateTime,
    @SerializedName("end_at") val endAt: LocalDateTime,
    val scope: String,
    @SerializedName("participant_count") val participantCount: Int,
    val isMine: Boolean,
    val writer: ChallengeWriterResponse
)