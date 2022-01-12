package com.semicolon.data.remote.request.exercise

import com.google.gson.annotations.SerializedName

data class StartMeasureExerciseRequest(
    @SerializedName("goal") val goal: Int,
    @SerializedName("goal_type") val goalType: String
)
