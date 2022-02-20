package com.semicolon.data.local.entity.level

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "level")
data class LevelRoomEntity(
    @PrimaryKey val levelId: Int,
    val foodImageUrl: String,
    val foodName: String,
    val calories: Int,
    val size: String,
    val level: Int,
    val message: String
)