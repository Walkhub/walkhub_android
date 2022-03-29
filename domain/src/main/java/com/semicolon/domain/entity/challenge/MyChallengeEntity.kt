package com.semicolon.domain.entity.challenge

import com.semicolon.domain.enums.ChallengeGoalScope
import com.semicolon.domain.enums.ChallengeGoalType
import org.threeten.bp.LocalDateTime

data class MyChallengeEntity(
    val id: Long,
    val name: String,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
    val imageUrl: String,
    val goal: Int,
    val goalScope: ChallengeGoalScope,
    val goalType: ChallengeGoalType,
    val totalWalkCount: Int,
    val writer: Writer
) {
    data class Writer(
        val id: Long,
        val name: String,
        val profileImageUrl: String
    )
}