package com.semicolon.data.remote.response.exercise

import com.google.gson.annotations.SerializedName

data class ExerciseAnalysisResultResponse(
    @SerializedName("level_id") val levelId: Int,
    @SerializedName("walk_count_list") val walkCountList: List<Int>,
    @SerializedName("daily_walk_count_goal") val dailyWalkCountGoal: Int,
    @SerializedName("walk_count") val walkCount: Int,
    @SerializedName("calories") val calories: Int,
    @SerializedName("distance") val distanceAsKiloMeter: Float,
    @SerializedName("walk_time") val walkTimeAsMinute: Int
)
