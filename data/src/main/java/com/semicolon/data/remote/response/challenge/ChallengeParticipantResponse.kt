package com.semicolon.data.remote.response.challenge

import com.google.gson.annotations.SerializedName

data class ChallengeParticipantResponse(
    val id: Long,
    val name: String,
    val gcn: Int,
    @SerializedName("profile_image_url") val profileImageUrl: String
)