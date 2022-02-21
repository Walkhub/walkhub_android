package com.semicolon.data.local.entity.level

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.level.LevelEntity

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

fun LevelRoomEntity.toEntity() =
    LevelEntity(
        levelId,
        foodImageUrl,
        foodName,
        calories,
        size,
        level,
        message
    )

fun List<LevelRoomEntity>.toEntity() =
    map { it.toEntity() }

fun LevelEntity.toRoomEntity() =
    LevelRoomEntity(
        levelId,
        foodImageUrl,
        foodName,
        calories,
        size,
        level,
        message
    )

fun List<LevelEntity>.toRoomEntity() =
    map { it.toRoomEntity() }