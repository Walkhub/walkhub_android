package com.semicolon.domain.enums

enum class GoalType(val value: String) {
    DISTANCE("DISTANCE"),
    WALK_COUNT("WALK")
}

fun String.toMeasureGoalType() =
    when(this) {
        "DISTANCE" -> GoalType.DISTANCE
        else -> GoalType.WALK_COUNT
    }