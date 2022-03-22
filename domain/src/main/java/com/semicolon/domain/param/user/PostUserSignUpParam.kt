package com.semicolon.domain.param.user

data class PostUserSignUpParam(
    val accountId: String,
    val password: String,
    val name: String,
    val phoneNumber: String,
    val height: Double,
    val weight: Double,
    val sex: String,
    val schoolId: Int,
    val authCode: String
)

