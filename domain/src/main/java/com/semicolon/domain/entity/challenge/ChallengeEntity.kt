package com.semicolon.domain.entity.challenge

import com.semicolon.domain.enum.UserScope
import org.threeten.bp.LocalDateTime

data class ChallengeEntity(
    val id: Int,
    val name: String,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
    val imageUrl: String,
    val scope: UserScope
)