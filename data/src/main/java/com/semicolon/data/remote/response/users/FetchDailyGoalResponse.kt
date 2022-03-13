package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName

data class FetchDailyGoalResponse(
    @SerializedName("daily_walk_count_goal") val dailyWalkCountGoal: Int
)
