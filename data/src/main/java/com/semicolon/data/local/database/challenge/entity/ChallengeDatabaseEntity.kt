package com.semicolon.data.local.database.challenge.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.enum.ChallengeScope
import java.time.LocalDateTime

@Entity(tableName = "challenge")
data class ChallengeDatabaseEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
    val imageUrl: String,
    val scope: ChallengeScope
)