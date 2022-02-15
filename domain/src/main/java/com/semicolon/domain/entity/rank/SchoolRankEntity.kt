package com.semicolon.domain.entity.rank

data class SchoolRankEntity(
    val mySchoolRank: MySchoolRank,
    val schoolRankList: List<SchoolRank>
) {
    data class SchoolRank(
        val schoolId: Int,
        val name: String,
        val ranking: Int,
        val studentCount: Int,
        val logoImageUrl: String,
        val walkCount: Int
    )

    data class MySchoolRank(
        val schoolId: Int,
        val name: String,
        val logoImageUrl: String,
        val walkCount: Int,
        val grade: Int,
        val classNum: Int
    )
}