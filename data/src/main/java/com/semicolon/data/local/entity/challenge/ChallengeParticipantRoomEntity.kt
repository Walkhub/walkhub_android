package com.semicolon.data.local.entity.challenge

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity

@Entity(tableName = "challenge_participants")
data class ChallengeParticipantRoomEntity(
    @PrimaryKey var id: Int,
    var name: String,
    var gcn: Int,
    var profileImageUrl: String,
    var challengeId: Int
)

fun ChallengeParticipantRoomEntity.toEntity() =
    ChallengeParticipantEntity(
        id = id,
        name = name,
        gcn = gcn,
        profileImageUrl = profileImageUrl
    )

fun List<ChallengeParticipantRoomEntity>.toEntity() =
    map { it.toEntity() }

fun ChallengeParticipantEntity.toDbEntity(challengeId: Int) =
    ChallengeParticipantRoomEntity(
        id = id,
        name = name,
        gcn = gcn,
        profileImageUrl = profileImageUrl,
        challengeId = challengeId
    )

fun List<ChallengeParticipantEntity>.toDbEntity(challengeId: Int) =
    map { it.toDbEntity(challengeId) }