package com.semicolon.domain.enum

enum class UserScope {
    ALL,
    SCHOOL,
    CLASS,
    GRADE,
    ETC
}

fun String?.toChallengeScope(): UserScope =
    when(this) {
        "ALL" -> UserScope.ALL
        "SCHOOL" -> UserScope.SCHOOL
        "CLASS" -> UserScope.CLASS
        "GRADE" -> UserScope.GRADE
        else -> UserScope.ETC
    }

fun UserScope.toScopeString(): String =
    when(this) {
        UserScope.ALL -> "ALL"
        UserScope.SCHOOL -> "SCHOOL"
        UserScope.CLASS -> "CLASS"
        UserScope.GRADE -> "GRADE"
        UserScope.ETC -> "ETC"
    }