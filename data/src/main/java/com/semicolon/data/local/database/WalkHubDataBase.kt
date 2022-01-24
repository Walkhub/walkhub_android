package com.semicolon.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.semicolon.data.local.dao.ChallengeDao
import com.semicolon.data.local.entity.challenge.ChallengeDetailRoomEntity
import com.semicolon.data.local.entity.challenge.ChallengeParticipantRoomEntity
import com.semicolon.data.local.entity.challenge.ChallengeRoomEntity

@Database(
    entities = [
        ChallengeRoomEntity::class,
        ChallengeDetailRoomEntity::class,
        ChallengeParticipantRoomEntity::class
    ], version = 1, exportSchema = false
)
abstract class WalkHubDataBase : RoomDatabase() {
    abstract fun challengeDao(): ChallengeDao
}