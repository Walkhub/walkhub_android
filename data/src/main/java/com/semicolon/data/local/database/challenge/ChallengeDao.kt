package com.semicolon.data.local.database.challenge

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.semicolon.data.local.database.challenge.entity.ChallengeDatabaseEntity
import com.semicolon.data.local.database.challenge.entity.ChallengeDetailDatabaseEntity
import com.semicolon.data.local.database.challenge.entity.ChallengeDetailJoinEntity

@Dao
interface ChallengeDao {

    @Query("SELECT * FROM challenge")
    suspend fun fetchChallenges(): List<ChallengeDatabaseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveChallenges(challenges: List<ChallengeDatabaseEntity>)

    @Query("SELECT * FROM challenge A JOIN challenge_detail B WHERE A.id = B.id")
    suspend fun fetchChallengeDetail(): ChallengeDetailJoinEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveChallengeDetail(challengeDetail: ChallengeDetailDatabaseEntity)
}