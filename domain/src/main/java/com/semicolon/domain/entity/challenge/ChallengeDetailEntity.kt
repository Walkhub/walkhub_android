package com.semicolon.domain.entity.challenge

import com.semicolon.domain.enum.ChallengeGoalType
import com.semicolon.domain.enum.ChallengeGoalScope
import com.semicolon.domain.enum.ChallengeUserScope
import org.threeten.bp.LocalDateTime

data class ChallengeDetailEntity(
    val name: String,
    val content: String,
    val goal: Int,
    val goalType: ChallengeGoalType,
    val goalScope: ChallengeGoalScope,
    val userScope: ChallengeUserScope,
    val award: String,
    val imageUrl: String,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
    val isMine: Boolean,
    val participantCount: Int,
    val writerEntity: WriterEntity
) {
    data class WriterEntity(
        val id: Int,
        val name: String,
        val profileImageUrl: String
    )
}