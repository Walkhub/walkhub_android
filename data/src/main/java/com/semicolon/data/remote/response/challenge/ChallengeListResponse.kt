package com.semicolon.data.remote.response.challenge

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.challenge.Challenge
import com.semicolon.domain.entity.challenge.toChallengeScope
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

    fun ChallengeResponse.toEntity(): Challenge =
        Challenge(
            id = id,
            name = name,
            startAt = startAt,
            endAt = endAt,
            imageUrl = imageUrl,
            scope = scope.toChallengeScope()
        )
}

fun ChallengeListResponse.toEntity(): List<Challenge> =
    challengeList.map { it.toEntity() }

