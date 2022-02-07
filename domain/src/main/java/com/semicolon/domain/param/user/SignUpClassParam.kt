package com.semicolon.domain.param.user

data class SignUpClassParam(
    val agencyCode: String,
    val grade: Int,
    val classRoom: Int,
    val classCode: String,
    val number: Int
)
