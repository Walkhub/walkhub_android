package com.semicolon.domain.enum

enum class ChallengeScope {
    ALL,
    SCHOOL,
    CLASS,
    ETC
}

fun String?.toChallengeScope(): ChallengeScope =
    when(this) {
        "ALL" -> ChallengeScope.ALL
        "SCH" -> ChallengeScope.SCHOOL
        "CLS" -> ChallengeScope.CLASS
        else -> ChallengeScope.ETC
    }

fun ChallengeScope.toScopeString(): String =
    when(this) {
        ChallengeScope.ALL -> "ALL"
        ChallengeScope.SCHOOL -> "SCH"
        ChallengeScope.CLASS -> "CLS"
        ChallengeScope.ETC -> "ETC"
    }