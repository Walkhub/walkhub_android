package com.semicolon.domain.entity.exercise

import com.semicolon.domain.enums.GoalType

data class GoalEntity(
    val goal: Int,
    val goalType: GoalType
)
