package com.semicolon.domain.param.user

data class PatchUserHealthParam(
    val height: Double,
    val weight: Int,
    val sex: String
)