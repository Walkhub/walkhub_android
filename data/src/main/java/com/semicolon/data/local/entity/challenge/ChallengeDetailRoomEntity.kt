package com.semicolon.data.local.entity.challenge

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.enums.toScopeString

@Entity
data class ChallengeDetailRoomEntity(
    @PrimaryKey var id: Int,
    var content: String,
    var goal: Int,
    var goalType: String,
    var goalScope: String,
    var userScope: String,
    var award: String,
    var isMine: Boolean,
    var participantCount: Int,
    var writerId: Int,
    var writerName: String,
    var writerImageUrl: String
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
        participantCount = participantCount,
        writerId = writerEntity.id,
        writerName = writerEntity.name,
        writerImageUrl = writerEntity.profileImageUrl
    )