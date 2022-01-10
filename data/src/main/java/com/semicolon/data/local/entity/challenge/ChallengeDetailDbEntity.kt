package com.semicolon.data.local.entity.challenge

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity

@Entity
data class ChallengeDetailDbEntity(
    @PrimaryKey var id: Int,
    var content: String,
    var goal: Int,
    var award: String,
    var participantCount: Int,
    var writerId: Int,
    var writerName: String,
    var writerImageUrl: String
)

fun ChallengeDetailEntity.toDbEntity(id: Int) =
    ChallengeDetailDbEntity(
        id = id,
        content = content,
        goal = goal,
        award = award,
        participantCount = participantCount,
        writerId = writerEntity.id,
        writerName = writerEntity.name,
        writerImageUrl = writerEntity.profileImageUrl
    )