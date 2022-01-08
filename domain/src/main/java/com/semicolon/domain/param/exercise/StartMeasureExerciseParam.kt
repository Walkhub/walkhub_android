package com.semicolon.domain.param.exercise

import com.semicolon.domain.enum.GoalType

data class StartMeasureExerciseParam(
    val goal: Int,
    val goalType: GoalType
)