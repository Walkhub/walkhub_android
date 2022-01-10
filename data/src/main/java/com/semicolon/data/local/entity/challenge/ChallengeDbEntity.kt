package com.semicolon.data.local.entity.challenge

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.enum.toChallengeScope
import com.semicolon.domain.enum.toScopeString
import java.util.*

@Entity(tableName = "challenge")
data class ChallengeDbEntity(
    @PrimaryKey var id: Int,
    var name: String,
    var startAt: Date,
    var endAt: Date,
    var imageUrl: String,
    var scope: String
)

fun ChallengeDbEntity.toEntity() =
    ChallengeEntity(
        id = id,
        name = name,
        startAt = startAt,
        endAt = endAt,
        imageUrl = imageUrl,
        scope = scope.toChallengeScope()
    )

fun List<ChallengeDbEntity>.toEntity() =
    map { it.toEntity() }

fun ChallengeEntity.toDatabaseEntity() =
    ChallengeDbEntity(
        id = id,
        name = name,
        startAt = startAt,
        endAt = endAt,
        imageUrl = imageUrl,
        scope = scope.toScopeString()
    )

fun List<ChallengeEntity>.toDatabaseEntity() =
    map { it.toDatabaseEntity() }