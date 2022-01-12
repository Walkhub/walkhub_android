package com.semicolon.data.local.entity.challenge

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.data.util.toLocalDateTime
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.enum.toChallengeScope
import com.semicolon.domain.enum.toScopeString

@Entity(tableName = "challenge")
data class ChallengeDbEntity(
    @PrimaryKey var id: Int,
    var name: String,
    var startAt: String,
    var endAt: String,
    var imageUrl: String,
    var scope: String
)

fun ChallengeDbEntity.toEntity() =
    ChallengeEntity(
        id = id,
        name = name,
        startAt = startAt.toLocalDateTime(),
        endAt = endAt.toLocalDateTime(),
        imageUrl = imageUrl,
        scope = scope.toChallengeScope()
    )

fun List<ChallengeDbEntity>.toEntity() =
    map { it.toEntity() }

fun ChallengeEntity.toDbEntity() =
    ChallengeDbEntity(
        id = id,
        name = name,
        startAt = startAt.toString(),
        endAt = endAt.toString(),
        imageUrl = imageUrl,
        scope = scope.toScopeString()
    )

fun List<ChallengeEntity>.toDbEntity() =
    map { it.toDbEntity() }