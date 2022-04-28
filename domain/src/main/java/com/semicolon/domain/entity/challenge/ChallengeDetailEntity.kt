package com.semicolon.domain.entity.challenge

import com.semicolon.domain.enums.ChallengeGoalType
import com.semicolon.domain.enums.ChallengeGoalScope
import com.semicolon.domain.enums.ChallengeUserScope
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
    val successStandard: Int,
    val value: Int,
    val writerEntity: WriterEntity,
    val isMine: Boolean,
    val isParticipated: Boolean,
    val participantCount: Int,
    val participantList: List<ParticipantList>,
    ) {
    data class WriterEntity(
        val id: Int,
        val name: String,
        val profileImageUrl: String
    )
    data class ParticipantList(
        val id: Int,
        val name: String,
        val profileImageUrl: String
    )
}