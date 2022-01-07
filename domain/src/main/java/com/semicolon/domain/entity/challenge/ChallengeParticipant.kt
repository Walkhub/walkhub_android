package com.semicolon.domain.entity.challenge

data class ChallengeParticipant(
    val id: Int,
    val name: String,
    val gcn: Int,
    val profileImageUrl: String
)