package com.semicolon.domain.entity.exercise

import com.semicolon.domain.enum.GoalType

data class GoalEntity(
    val goal: Int,
    val goalType: GoalType
)
