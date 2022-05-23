package com.semicolon.domain.param.user

import java.io.File

data class UpdateProfileParam(
    val name: String,
    val profileImage: File?,
    val schoolId: Long
)