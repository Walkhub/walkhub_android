package com.semicolon.domain.entity.rank

data class FetchMySchoolRankEntity(
    val schoolId: Int,
    val name: String,
    val logoImageUrl: String,
    val grade: Int?,
    val classNum: Int?
)