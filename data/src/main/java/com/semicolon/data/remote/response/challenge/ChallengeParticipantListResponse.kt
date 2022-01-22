package com.semicolon.data.remote.response.challenge

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity

data class ChallengeParticipantListResponse(
    @SerializedName("participant_list") val participantList: List<ChallengeParticipantResponse>
) {
    data class ChallengeParticipantResponse(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("gcn") val gcn: Int,
        @SerializedName("profile_image_url") val profileImageUrl: String
    )

    fun ChallengeParticipantResponse.toEntity() =
        ChallengeParticipantEntity(
            id = id,
            name = name,
            gcn = gcn,
            profileImageUrl = profileImageUrl
        )
}

fun ChallengeParticipantListResponse.toEntity() =
    participantList.map { it.toEntity() }