package com.semicolon.domain.entity.users

data class FetchInfoEntity(
    val name: String,
    val profileImageUrl: String,
    val schoolName: String,
    val grade: Int,
    val classNum: Int
)
