package com.semicolon.domain.param.user

data class UpdateProfileParam(
    val birthday: Int,
    val name: String,
    val profileUrl: String,
    val sex: String
)