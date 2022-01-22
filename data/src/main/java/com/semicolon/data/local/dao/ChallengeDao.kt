package com.semicolon.data.local.dao

import androidx.room.*
import com.semicolon.data.local.entity.challenge.ChallengeAndDetail
import com.semicolon.data.local.entity.challenge.ChallengeRoomEntity
import com.semicolon.data.local.entity.challenge.ChallengeDetailRoomEntity
import com.semicolon.data.local.entity.challenge.ChallengeParticipantRoomEntity

@Dao
interface ChallengeDao {

    @Query("SELECT * FROM challenge")
    suspend fun fetchChallenges(): List<ChallengeRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveChallenges(challenges: List<ChallengeRoomEntity>)

    @Transaction
    @Query("SELECT * FROM challenge WHERE id = :challengeId")
    suspend fun fetchChallengeDetail(challengeId: Int): ChallengeAndDetail

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveChallengeDetail(challengeDetail: ChallengeDetailRoomEntity)

    @Query("SELECT * FROM challenge_participants WHERE challengeId = :challengeId")
    suspend fun fetchParticipants(challengeId: Int): List<ChallengeParticipantRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveParticipants(participants: List<ChallengeParticipantRoomEntity>)
}