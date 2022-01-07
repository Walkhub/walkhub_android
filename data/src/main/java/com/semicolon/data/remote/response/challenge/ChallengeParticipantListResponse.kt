package com.semicolon.data.remote.response.challenge

import com.google.gson.annotations.SerializedName

data class ChallengeParticipantListResponse(
    @SerializedName("participant_list") val participantList: List<ChallengeParticipantResponse>
) {
    data class ChallengeParticipantResponse(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("gcn") val gcn: Int,
        @SerializedName("profile_image_url") val profileImageUrl: String
    )
}