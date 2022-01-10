package com.semicolon.data.remote.response.challenge

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.enum.toChallengeScope
import java.util.*

data class ChallengeListResponse(
    @SerializedName("challenge_list") val challengeList: List<ChallengeResponse>
) {
    data class ChallengeResponse(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("start_at") val startAt: Date,
        @SerializedName("end_at") val endAt: Date,
        @SerializedName("image_url") val imageUrl: String,
        @SerializedName("scope") val scope: String
    )

    fun ChallengeResponse.toEntity(): ChallengeEntity =
        ChallengeEntity(
            id = id,
            name = name,
            startAt = startAt,
            endAt = endAt,
            imageUrl = imageUrl,
            scope = scope.toChallengeScope()
        )
}

fun ChallengeListResponse.toEntity(): List<ChallengeEntity> =
    challengeList.map { it.toEntity() }

