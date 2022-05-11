package com.semicolon.domain.param.user

data class UpdateProfileParam(
    val name: String,
    val profileImage: String,
    val schoolId: Long
)