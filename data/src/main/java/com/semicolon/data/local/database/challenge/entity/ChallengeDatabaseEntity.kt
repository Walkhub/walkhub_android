package com.semicolon.data.local.database.challenge.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.challenge.ChallengeEntity
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

fun ChallengeDatabaseEntity.toEntity() =
    ChallengeEntity(
        id = id,
        name = name,
        startAt = startAt,
        endAt = endAt,
        imageUrl = imageUrl,
        scope = scope
    )

fun List<ChallengeDatabaseEntity>.toEntity() =
    map { it.toEntity() }

fun ChallengeEntity.toDatabaseEntity() =
    ChallengeDatabaseEntity(
        id = id,
        name = name,
        startAt = startAt,
        endAt = endAt,
        imageUrl = imageUrl,
        scope = scope
    )

fun List<ChallengeEntity>.toDatabaseEntity() =
    map { it.toDatabaseEntity() }