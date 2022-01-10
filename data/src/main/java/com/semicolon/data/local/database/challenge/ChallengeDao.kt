package com.semicolon.data.local.database.challenge

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.semicolon.data.local.database.challenge.entity.ChallengeDatabaseEntity

@Dao
interface ChallengeDao {

    @Query("SELECT * FROM challenge")
    suspend fun fetchChallenges(): List<ChallengeDatabaseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveChallenges(challenges: List<ChallengeDatabaseEntity>)
}