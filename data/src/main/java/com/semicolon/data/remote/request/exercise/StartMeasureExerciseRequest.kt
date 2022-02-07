package com.semicolon.data.remote.request.exercise

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.param.exercise.StartMeasureExerciseParam

data class StartMeasureExerciseRequest(
    @SerializedName("goal") val goal: Int,
    @SerializedName("goal_type") val goalType: String
)

fun StartMeasureExerciseParam.toRequest() = StartMeasureExerciseRequest(
    goal = goal,
    goalType = goalType.value
)
