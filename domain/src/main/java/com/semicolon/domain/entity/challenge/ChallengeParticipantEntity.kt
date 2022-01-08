package com.semicolon.domain.entity.challenge

data class ChallengeParticipantEntity(
    val id: Int,
    val name: String,
    val gcn: Int,
    val profileImageUrl: String
)