package com.semicolon.data.remote.response.challenge

import com.google.gson.annotations.SerializedName
import com.semicolon.data.util.toLocalDateTime
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.enum.toChallengeScope

data class ChallengeListResponse(
    @SerializedName("challenge_list") val challengeList: List<ChallengeResponse>
) {
    data class ChallengeResponse(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("start_at") val startAt: String,
        @SerializedName("end_at") val endAt: String,
        @SerializedName("image_url") val imageUrl: String,
        @SerializedName("scope") val scope: String
    )

    fun ChallengeResponse.toEntity(): ChallengeEntity =
        ChallengeEntity(
            id = id,
            name = name,
            startAt = startAt.toLocalDateTime(),
            endAt = endAt.toLocalDateTime(),
            imageUrl = imageUrl,
            scope = scope.toChallengeScope()
        )
}

fun ChallengeListResponse.toEntity(): List<ChallengeEntity> =
    challengeList.map { it.toEntity() }

