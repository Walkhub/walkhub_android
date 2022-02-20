package com.semicolon.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.semicolon.data.local.entity.level.LevelRoomEntity

interface LevelDao {

    @Query("SELECT * FROM level")
    suspend fun fetchLevelList(): List<LevelRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLevelList(levelList: List<LevelRoomEntity>)
}