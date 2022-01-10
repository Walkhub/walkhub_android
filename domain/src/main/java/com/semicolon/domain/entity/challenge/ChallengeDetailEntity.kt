package com.semicolon.domain.entity.challenge

import com.semicolon.domain.enum.ChallengeScope
import java.util.*

data class ChallengeDetailEntity(
    val name: String,
    val content: String,
    val goal: Int,
    val award: String,
    val imageUrl: String,
    val startAt: Date,
    val endAt: Date,
    val scope: ChallengeScope,
    val participantCount: Int,
    val writerEntity: WriterEntity
) {
    data class WriterEntity(
        val id: Int,
        val name: String,
        val profileImageUrl: String
    )
}