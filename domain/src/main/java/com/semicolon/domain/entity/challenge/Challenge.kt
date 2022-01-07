package com.semicolon.domain.entity.challenge

import java.time.LocalDateTime

data class Challenge(
    val id: Int,
    val name: String,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
    val imageUrl: String,
    val scope: ChallengeScope
)