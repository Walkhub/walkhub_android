package com.semicolon.data.local.entity.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.users.FetchDailyGoalEntity

@Entity(tableName = "dailyGoal")
data class FetchDailyGoalRoomEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val dailyWalkCountGoal: Int
)

fun FetchDailyGoalRoomEntity.toEntity() =
    FetchDailyGoalEntity(
        dailyWalkCountGoal = dailyWalkCountGoal
    )

fun FetchDailyGoalEntity.toDbEntity() =
    FetchDailyGoalRoomEntity(
        dailyWalkCountGoal = dailyWalkCountGoal
    )
