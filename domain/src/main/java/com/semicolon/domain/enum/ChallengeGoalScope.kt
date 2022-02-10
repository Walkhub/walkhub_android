package com.semicolon.domain.enum

enum class ChallengeGoalScope {
    ALL,
    DAY,
    ETC
}

fun String.toGoalScope() =
    when(this) {
        "ALL" -> ChallengeGoalScope.ALL
        "DAY" -> ChallengeGoalScope.DAY
        else -> ChallengeGoalScope.ETC
    }