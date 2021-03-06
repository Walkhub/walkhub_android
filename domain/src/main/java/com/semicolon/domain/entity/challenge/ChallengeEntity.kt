package com.semicolon.domain.entity.challenge

import com.semicolon.domain.enums.ChallengeGoalScope
import com.semicolon.domain.enums.ChallengeGoalType
import org.threeten.bp.LocalDateTime

data class ChallengeEntity(
    val id: Int,
    val name: String,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
    val imageUrl: String,
    val goal: Int,
    val goalScope: ChallengeGoalScope,
    val goalType: ChallengeGoalType,
    val award: String,
    val writer: Writer,
    val participantCount: Int,
    val participantList: List<ChallengeParticipantEntity>
) {

    data class Writer(
        val userId: Int,
        val name: String,
        val profileImageUrl: String
    )
}