package com.semicolon.data.local.database.challenge

import androidx.room.*
import com.semicolon.data.local.database.challenge.entity.ChallengeAndDetail
import com.semicolon.data.local.database.challenge.entity.ChallengeDBEntity
import com.semicolon.data.local.database.challenge.entity.ChallengeDetailDBEntity

@Dao
interface ChallengeDao {

    @Query("SELECT * FROM challenge")
    suspend fun fetchChallenges(): List<ChallengeDBEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveChallenges(challenges: List<ChallengeDBEntity>)

    @Transaction
    @Query("SELECT * FROM challenge WHERE id = :challengeId")
    suspend fun fetchChallengeDetail(challengeId: Int): ChallengeAndDetail

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveChallengeDetail(challengeDetail: ChallengeDetailDBEntity)
}