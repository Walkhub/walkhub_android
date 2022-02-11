package com.semicolon.data.remote.request.users

import com.google.gson.annotations.SerializedName

data class PatchDailyWalkGoalRequest(
    @SerializedName("daily_walk_count_goal") val dailyWalkCountGoal: Int
)
