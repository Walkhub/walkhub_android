package com.semicolon.domain.entity.challenge

import com.semicolon.domain.enums.ChallengeGoalScope
import com.semicolon.domain.enums.ChallengeGoalType
import com.semicolon.domain.enums.ChallengeUserScope
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