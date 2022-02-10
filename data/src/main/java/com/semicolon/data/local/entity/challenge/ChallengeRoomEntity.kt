package com.semicolon.data.local.entity.challenge

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.data.util.toLocalDateTime
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.enum.toUserScope
import com.semicolon.domain.enum.toScopeString

@Entity(tableName = "challenge")
data class ChallengeRoomEntity(
    @PrimaryKey var id: Int,
    var name: String,
    var startAt: String,
    var endAt: String,
    var imageUrl: String,
    var scope: String
)

fun ChallengeRoomEntity.toEntity() =
    ChallengeEntity(
        id = id,
        name = name,
        startAt = startAt.toLocalDateTime(),
        endAt = endAt.toLocalDateTime(),
        imageUrl = imageUrl,
        scope = scope.toUserScope()
    )

fun List<ChallengeRoomEntity>.toEntity() =
    map { it.toEntity() }

fun ChallengeEntity.toDbEntity() =
    ChallengeRoomEntity(
        id = id,
        name = name,
        startAt = startAt.toString(),
        endAt = endAt.toString(),
        imageUrl = imageUrl,
        scope = scope.toScopeString()
    )

fun List<ChallengeEntity>.toDbEntity() =
    map { it.toDbEntity() }