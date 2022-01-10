package com.semicolon.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.semicolon.data.local.database.challenge.ChallengeDao
import com.semicolon.data.local.database.challenge.entity.ChallengeDatabaseEntity
import com.semicolon.data.local.database.converter.DateConverter

@Database(entities = [ChallengeDatabaseEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class WalkHubDataBase : RoomDatabase(){
    abstract fun challengeDao(): ChallengeDao
}