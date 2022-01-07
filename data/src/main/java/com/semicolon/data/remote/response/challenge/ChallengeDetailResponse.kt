package com.semicolon.data.remote.response.challenge

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class ChallengeDetailResponse(
    @SerializedName("name") val name: String,
    @SerializedName("content") val content: String,
    @SerializedName("goal") val goal: Int,
    @SerializedName("award") val award: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("start_at") val startAt: LocalDateTime,
    @SerializedName("end_at") val endAt: LocalDateTime,
    @SerializedName("scope") val scope: String,
    @SerializedName("participant_count") val participantCount: Int,
    @SerializedName("isMine") val isMine: Boolean,
    @SerializedName("writer") val writer: ChallengeWriterResponse
) {
    data class ChallengeWriterResponse(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("profile_image_url") val profileUrl: String
    )
}