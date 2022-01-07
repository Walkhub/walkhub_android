package com.semicolon.domain.param.exercise

import com.semicolon.domain.enum.GoalType

data class StartExerciseMeasureParam(
    val goal: Int,
    val goalType: GoalType
)