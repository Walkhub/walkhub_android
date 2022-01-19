package com.semicolon.domain.param.user

data class PostUserSignInParam(
    val accountId: String,
    val password: String,
    val deviceToken: String
)

