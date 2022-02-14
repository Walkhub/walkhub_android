package com.semicolon.domain.enum

enum class ChallengeGoalType {
    WALK,
    DISTANCE,
    ETC
}

fun String.toGoalType(): ChallengeGoalType =
    when(this) {
        "WALK" -> ChallengeGoalType.WALK
        "DISTANCE" -> ChallengeGoalType.DISTANCE
        else -> ChallengeGoalType.ETC
    }

fun ChallengeGoalType.toScopeString() =
    when(this) {
        ChallengeGoalType.WALK -> "WALK"
        ChallengeGoalType.DISTANCE -> "DISTANCE"
        ChallengeGoalType.ETC -> "ETC"
    }