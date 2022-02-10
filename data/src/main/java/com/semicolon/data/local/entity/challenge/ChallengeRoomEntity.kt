package com.semicolon.data.local.entity.challenge

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.data.util.toLocalDateTime
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.enum.toGoalScope
import com.semicolon.domain.enum.toGoalType
import com.semicolon.domain.enum.toUserScope
import com.semicolon.domain.enum.toScopeString

@Entity(tableName = "challenge")
data class ChallengeRoomEntity(
    @PrimaryKey var id: Int,
    var name: String,
    var startAt: String,
    var endAt: String,
    var imageUrl: String,
    var goalType: String,
    var goalScope: String,
    var userScope: String
)

fun ChallengeRoomEntity.toEntity() =
    ChallengeEntity(
        id = id,
        name = name,
        startAt = startAt.toLocalDateTime(),
        endAt = endAt.toLocalDateTime(),
        imageUrl = imageUrl,
        userScope = userScope.toUserScope(),
        goalScope = goalScope.toGoalScope(),
        goalType = goalType.toGoalType()
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
        userScope = userScope.toScopeString(),
        goalScope = goalScope.toScopeString(),
        goalType = goalType.toScopeString()
    )

fun List<ChallengeEntity>.toDbEntity() =
    map { it.toDbEntity() }