package com.semicolon.domain.param.teacher

data class DeleteClassParam(
    val grade: Int,
    val classes: Int,
    val agencyCode: String
)