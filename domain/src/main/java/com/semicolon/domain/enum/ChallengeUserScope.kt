package com.semicolon.domain.enum

enum class ChallengeUserScope {
    ALL,
    SCHOOL,
    CLASS,
    GRADE,
    ETC
}

fun String?.toUserScope(): ChallengeUserScope =
    when(this) {
        "ALL" -> ChallengeUserScope.ALL
        "SCHOOL" -> ChallengeUserScope.SCHOOL
        "CLASS" -> ChallengeUserScope.CLASS
        "GRADE" -> ChallengeUserScope.GRADE
        else -> ChallengeUserScope.ETC
    }

fun ChallengeUserScope.toScopeString(): String =
    when(this) {
        ChallengeUserScope.ALL -> "ALL"
        ChallengeUserScope.SCHOOL -> "SCHOOL"
        ChallengeUserScope.CLASS -> "CLASS"
        ChallengeUserScope.GRADE -> "GRADE"
        ChallengeUserScope.ETC -> "ETC"
    }