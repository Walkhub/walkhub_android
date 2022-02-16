package com.semicolon.domain.entity.challenge

import com.semicolon.domain.enum.ChallengeGoalScope
import com.semicolon.domain.enum.ChallengeGoalType
import com.semicolon.domain.enum.ChallengeUserScope
import org.threeten.bp.LocalDateTime

data class ChallengeEntity(
    val id: Int,
    val name: String,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
    val imageUrl: String,
    val userScope: ChallengeUserScope,
    val goalScope: ChallengeGoalScope,
    val goalType: ChallengeGoalType
)