package com.semicolon.data.local.dao

import androidx.room.Query
import com.semicolon.data.local.entity.level.LevelRoomEntity

interface LevelDao {

    @Query("SELECT * FROM level")
    fun fetchLevelList(): List<LevelRoomEntity>
}