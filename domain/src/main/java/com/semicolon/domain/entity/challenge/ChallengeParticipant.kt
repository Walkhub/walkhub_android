package com.semicolon.domain.entity.challenge

data class ChallengeParticipant(
    val id: Long,
    val name: String,
    val gcn: Int,
    val profileImageUrl: String
)