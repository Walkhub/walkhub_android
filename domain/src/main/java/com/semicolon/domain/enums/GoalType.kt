package com.semicolon.domain.enums

enum class GoalType(val value: String) {
    DISTANCE("D"),
    WALK_COUNT("W")
}

fun String.toMeasureGoalType() =
    when(this) {
        "D" -> GoalType.DISTANCE
        else -> GoalType.WALK_COUNT
    }