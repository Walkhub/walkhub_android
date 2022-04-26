package com.semicolon.data.local.entity.challenge

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity
import com.semicolon.domain.enums.toScopeString

@Entity
data class ChallengeDetailRoomEntity(
    @PrimaryKey var id: Int,
    var content: String,
    var userScope: String,
    var goal: Int,
    var goalType: String,
    var goalScope: String,
    var award: String,
    var isMine: Boolean,
    var isParticipated: Boolean,
    var participantCount: Int,
    var successStandard: Int,
    var value: Int,
    var writerId: Int,
    var writerName: String,
    var writerImageUrl: String,
    val participantList: List<ParticipantList>
) {
    data class ParticipantList(
        var participantUserId: Int,
        var participantName: String,
        var participantProfileImageUrl: String
    )
}

fun ChallengeDetailEntity.ParticipantList.toEntity() =
    ChallengeDetailRoomEntity.ParticipantList(
        participantUserId = id,
        participantName = name,
        participantProfileImageUrl = profileImageUrl
    )

fun ChallengeParticipantEntity.toParticipantEntity() =
    ChallengeDetailRoomEntity.ParticipantList(
        participantUserId = id,
        participantName = name,
        participantProfileImageUrl = profileImageUrl
    )

fun ChallengeDetailRoomEntity.ParticipantList.toEntity() =
    ChallengeDetailEntity.ParticipantList(
        id = participantUserId,
        name = participantName,
        profileImageUrl = participantProfileImageUrl
    )

fun ChallengeDetailEntity.toDbEntity(id: Int) =
    ChallengeDetailRoomEntity(
        id = id,
        content = content,
        goal = goal,
        goalType = goalType.toScopeString(),
        goalScope = goalScope.toScopeString(),
        userScope = userScope.toScopeString(),
        award = award,
        isMine = isMine,
        isParticipated = isParticipated,
        participantCount = participantCount,
        successStandard = successStandard,
        value = value,
        writerId = writerEntity.id,
        writerName = writerEntity.name,
        writerImageUrl = writerEntity.profileImageUrl,
        participantList = participantList.map { it.toEntity() }
    )