package com.semicolon.domain.entity.challenge

import com.semicolon.domain.enums.ChallengeGoalScope
import com.semicolon.domain.enums.ChallengeGoalType
import java.time.LocalDate

data class MyChallengeEntity(
    val id: Long,
    val name: String,
    val startAt: LocalDate,
    val endAt: LocalDate,
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