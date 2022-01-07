package com.semicolon.domain.entity.challenge

enum class ChallengeScope {
    ALL,
    SCHOOL,
    CLASS,
    ETC
}

fun String.toChallengeScope(): ChallengeScope =
    when(this) {
        "ALL" -> ChallengeScope.ALL
        "SCH" -> ChallengeScope.SCHOOL
        "CLS" -> ChallengeScope.CLASS
        else -> ChallengeScope.ETC
    }