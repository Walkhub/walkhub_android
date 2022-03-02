package com.semicolon.domain.param.exercise

import com.semicolon.domain.enums.GoalType

data class StartMeasureExerciseParam(
    val goal: Int,
    val goalType: GoalType
)