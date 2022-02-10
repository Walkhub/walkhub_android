package com.semicolon.domain.entity.challenge

import com.semicolon.domain.enum.GoalScope
import com.semicolon.domain.enum.GoalType
import com.semicolon.domain.enum.UserScope
import org.threeten.bp.LocalDateTime

data class ChallengeDetailEntity(
    val name: String,
    val content: String,
    val goal: Int,
    val goalType: GoalType,
    val goalScope: GoalScope,
    val userScope: UserScope,
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