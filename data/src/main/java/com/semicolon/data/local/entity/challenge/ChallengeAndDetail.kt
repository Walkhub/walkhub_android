package com.semicolon.data.local.entity.challenge

import androidx.room.Embedded
import androidx.room.Relation
import com.semicolon.data.util.toLocalDateTime
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.enums.toGoalScope
import com.semicolon.domain.enums.toGoalType
import com.semicolon.domain.enums.toUserScope

data class ChallengeAndDetail(

    @Embedded val challenge: ChallengeRoomEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val detail: ChallengeDetailRoomEntity
)

fun ChallengeAndDetail.toEntity() =
    ChallengeDetailEntity(
        name = challenge.name,
        content = detail.content,
        goal = detail.goal,
        goalType = detail.goalType.toGoalType(),
        goalScope = detail.goalScope.toGoalScope(),
        userScope = detail.userScope.toUserScope(),
        award = detail.award,
        imageUrl = challenge.imageUrl,
        isMine = detail.isMine,
        isParticipated = detail.isParticipated,
        startAt = challenge.startAt.toLocalDateTime(),
        endAt = challenge.endAt.toLocalDateTime(),
        value = detail.value,
        successStandard = detail.successStandard,
        participantCount = detail.participantCount,
        writerEntity = ChallengeDetailEntity.WriterEntity(
            id = detail.writerId,
            name = detail.writerName,
            profileImageUrl = detail.writerImageUrl
        ),
        participantList = detail.participantList.map { it.toEntity() }
    )