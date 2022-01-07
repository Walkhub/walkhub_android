package com.semicolon.data.remote.response.challenge

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class ChallengeListResponse(
    @SerializedName("challenge_list") val challengeList: List<ChallengeResponse>
) {
    data class ChallengeResponse(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("start_at") val startAt: LocalDateTime,
        @SerializedName("end_at") val endAt: LocalDateTime,
        @SerializedName("image_url") val imageUrl: String,
        @SerializedName("scope") val scope: String
    )
}