package com.semicolon.data.local.database.challenge.entity

import androidx.room.Embedded

data class ChallengeDetailJoinEntity(

    @Embedded
    val challenge: ChallengeDatabaseEntity,

    @Embedded
    val challengeDetailDatabaseEntity: ChallengeDetailDatabaseEntity
)