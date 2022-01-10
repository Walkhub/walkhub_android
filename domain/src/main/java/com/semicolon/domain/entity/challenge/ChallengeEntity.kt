package com.semicolon.domain.entity.challenge

import com.semicolon.domain.enum.ChallengeScope
import java.util.*

data class ChallengeEntity(
    val id: Int,
    val name: String,
    val startAt: Date,
    val endAt: Date,
    val imageUrl: String,
    val scope: ChallengeScope
)