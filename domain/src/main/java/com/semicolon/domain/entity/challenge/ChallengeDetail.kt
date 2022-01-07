package com.semicolon.domain.entity.challenge

import java.time.LocalDateTime

data class ChallengeDetail(
    val name: String,
    val content: String,
    val goal: Int,
    val award: String,
    val imageUrl: String,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
    val scope: ChallengeScope,
    val participantCount: Int,
    val writer: Writer
) {
    data class Writer(
        val id: Int,
        val name: String,
        val profileImageUrl: String
    )
}