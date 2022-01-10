package com.semicolon.data.local.database.challenge.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenge_detail")
data class ChallengeDetailDatabaseEntity(
    @PrimaryKey var id: Int,
    var content: String,
    var goal: Int,
    var award: String,
    var participantCount: Int,
    var writerId: Int,
    var writerName: String,
    var writerImageUrl: String
)