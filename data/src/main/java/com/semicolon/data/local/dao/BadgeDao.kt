package com.semicolon.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.semicolon.data.local.entity.badge.FetchMyBadgesRoomEntity
import com.semicolon.data.local.entity.badge.FetchNewBadgesRoomEntity
import com.semicolon.data.local.entity.badge.FetchUserBadgesRoomEntity
import com.semicolon.domain.entity.badge.FetchMyBadgesEntity
import com.semicolon.domain.entity.badge.FetchNewBadgesEntity
import com.semicolon.domain.entity.badge.FetchUserBadgesEntity

@Dao
interface BadgeDao {

    @Query("SELECT * FROM userBadges WHERE userId = :userId")
    suspend fun fetchUserBadges(userId: Int): FetchUserBadgesRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserBadge(fetchUserBadgesRoomEntity: FetchUserBadgesRoomEntity)

    @Query("SELECT * FROM myBadges")
    suspend fun fetchMyBadges(): FetchMyBadgesRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMyBadges(fetchMyBadgesRoomEntity: FetchMyBadgesRoomEntity)

    @Query("SELECT * FROM newBadges")
    suspend fun fetchNewBadges(): FetchNewBadgesRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewBadges(fetchNewBadgesRoomEntity: FetchNewBadgesRoomEntity)
}