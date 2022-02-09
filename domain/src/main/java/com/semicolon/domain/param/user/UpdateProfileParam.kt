package com.semicolon.domain.param.user

data class UpdateProfileParam(
    val name: String,
    val profileImageUrl: String,
    val sex: String
)