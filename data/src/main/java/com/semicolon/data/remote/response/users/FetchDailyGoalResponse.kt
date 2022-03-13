package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.users.FetchDailyGoalEntity

data class FetchDailyGoalResponse(
    @SerializedName("daily_walk_count_goal") val dailyWalkCountGoal: Int
)

fun FetchDailyGoalResponse.toEntity() =
    FetchDailyGoalEntity(
        dailyWalkCountGoal = dailyWalkCountGoal
    )
