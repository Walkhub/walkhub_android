package com.semicolon.data.local.dao.challenge

import androidx.room.*
import com.semicolon.data.local.entity.challenge.ChallengeAndDetail
import com.semicolon.data.local.entity.challenge.ChallengeDbEntity
import com.semicolon.data.local.entity.challenge.ChallengeDetailDbEntity
import com.semicolon.data.local.entity.challenge.ChallengeParticipantDbEntity

@Dao
interface ChallengeDao {

    @Query("SELECT * FROM challenge")
    suspend fun fetchChallenges(): List<ChallengeDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveChallenges(challenges: List<ChallengeDbEntity>)

    @Transaction
    @Query("SELECT * FROM challenge WHERE id = :challengeId")
    suspend fun fetchChallengeDetail(challengeId: Int): ChallengeAndDetail

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveChallengeDetail(challengeDetail: ChallengeDetailDbEntity)

    @Query("SELECT * FROM challenge_participants WHERE challengeId = :challengeId")
    suspend fun fetchParticipants(challengeId: Int): List<ChallengeParticipantDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveParticipants(participants: List<ChallengeParticipantDbEntity>)
}