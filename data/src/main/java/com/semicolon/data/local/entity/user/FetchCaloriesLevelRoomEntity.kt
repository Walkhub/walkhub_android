package com.semicolon.data.local.entity.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.users.FetchCaloriesLevelEntity

@Entity(tableName = "caloriesLevel")
data class FetchCaloriesLevelRoomEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val caloriesLevelList: List<CaloriesLevel>
) {
    data class CaloriesLevel(
        val calorie: Int,
        val foodImageUrl: String,
        val foodName: String,
        val level: Int,
        val levelId: Int,
        val message: String,
        val size: String
    )

    fun CaloriesLevel.toEntity() =
        FetchCaloriesLevelEntity.CaloriesLevel(
            calorie = calorie,
            foodImageUrl = foodImageUrl,
            foodName = foodName,
            level = level,
            levelId = levelId,
            message = message,
            size = size
        )
}

fun FetchCaloriesLevelRoomEntity.toEntity() =
    FetchCaloriesLevelEntity(
        caloriesLevelList = caloriesLevelList.map { it.toEntity() }
    )

fun FetchCaloriesLevelEntity.CaloriesLevel.toDbEntity() =
    FetchCaloriesLevelRoomEntity.CaloriesLevel(
        calorie = calorie,
        foodImageUrl = foodImageUrl,
        foodName = foodName,
        level = level,
        levelId = levelId,
        message = message,
        size = size
    )

fun FetchCaloriesLevelEntity.toDbEntity() =
    FetchCaloriesLevelRoomEntity(
        caloriesLevelList = caloriesLevelList.map { it.toDbEntity() }
    )