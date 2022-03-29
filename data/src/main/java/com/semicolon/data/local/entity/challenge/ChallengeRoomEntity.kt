package com.semicolon.data.local.entity.challenge

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.data.util.toLocalDateTime
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity
import com.semicolon.domain.enums.toGoalScope
import com.semicolon.domain.enums.toGoalType
import com.semicolon.domain.enums.toScopeString

@Entity(tableName = "challenge")
data class ChallengeRoomEntity(
    @PrimaryKey var id: Int,
    var name: String,
    var startAt: String,
    var endAt: String,
    var imageUrl: String,
    var goalType: String,
    var goalScope: String,
    var award: String,
    var writerId: Long,
    var writerName: String,
    var writerProfileUrl: String,
    var participantCount: Int,
    var participantList: List<ChallengeParticipantEntity>
)

fun ChallengeRoomEntity.toEntity() =
    ChallengeEntity(
        id = id,
        name = name,
        startAt = startAt.toLocalDateTime(),
        endAt = endAt.toLocalDateTime(),
        imageUrl = imageUrl,
        goalScope = goalScope.toGoalScope(),
        goalType = goalType.toGoalType(),
        award = award,
        writer = ChallengeEntity.Writer(
            userId = writerId,
            name = writerName,
            profileImageUrl = writerProfileUrl
        ),
        participantCount = participantCount,
        participantList = participantList
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
        goalScope = goalScope.toScopeString(),
        goalType = goalType.toScopeString(),
        award = award,
        writerId = writer.userId,
        writerName = writer.name,
        writerProfileUrl = writer.profileImageUrl,
        participantCount = participantCount,
        participantList = participantList
    )

fun List<ChallengeEntity>.toDbEntity() =
    map { it.toDbEntity() }