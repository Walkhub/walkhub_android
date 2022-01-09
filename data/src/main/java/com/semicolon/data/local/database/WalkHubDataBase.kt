package com.semicolon.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.semicolon.data.local.database.challenge.ChallengeDao
import com.semicolon.data.local.database.challenge.entity.ChallengeDatabaseEntity

@Database(entities = [ChallengeDatabaseEntity::class], version = 1)
abstract class WalkHubDataBase : RoomDatabase(){
    abstract fun challengeDao(): ChallengeDao
}