package com.semicolon.data.remote.response.challenge

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity

data class ChallengeParticipantListResponse(
    @SerializedName("challenge_participants_count") val participantCount: Int,
    @SerializedName("challenge_participants_list") val participantList: List<ChallengeParticipantResponse>
) {
    data class ChallengeParticipantResponse(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("profile_image_url") val profileImageUrl: String
    )

    fun ChallengeParticipantResponse.toEntity() =
        ChallengeParticipantEntity(
            id = id,
            name = name,
            profileImageUrl = profileImageUrl
        )
}

fun ChallengeParticipantListResponse.toEntity() =
    participantList.map { it.toEntity() }