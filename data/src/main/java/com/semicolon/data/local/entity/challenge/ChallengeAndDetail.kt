package com.semicolon.data.local.entity.challenge

import androidx.room.Embedded
import androidx.room.Relation
import com.semicolon.data.util.toLocalDateTime
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.enum.toChallengeScope

data class ChallengeAndDetail(

    @Embedded val challenge: ChallengeDbEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val detail: ChallengeDetailDbEntity
)

fun ChallengeAndDetail.toEntity() =
    ChallengeDetailEntity(
        name = challenge.name,
        content = detail.content,
        goal = detail.goal,
        award = detail.award,
        imageUrl = challenge.imageUrl,
        startAt = challenge.startAt.toLocalDateTime(),
        endAt = challenge.endAt.toLocalDateTime(),
        scope = challenge.scope.toChallengeScope(),
        participantCount = detail.participantCount,
        writerEntity = ChallengeDetailEntity.WriterEntity(
            id = detail.writerId,
            name = detail.writerName,
            profileImageUrl = detail.writerImageUrl
        )
    )