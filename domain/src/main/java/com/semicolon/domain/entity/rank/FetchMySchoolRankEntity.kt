package com.semicolon.domain.entity.rank

data class FetchMySchoolRankEntity(
    val mySchoolRank: MySchoolRank
) {
    data class MySchoolRank(
        val schoolId: Int,
        val name: String,
        val logoImageUrl: String,
        val grade: Int?,
        val classNum: Int?
    )
}