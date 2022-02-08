package com.semicolon.domain.entity.rank

data class SchoolRankEntity(
    val mySchoolRank: MySchoolRank,
    val schoolRankList: List<SchoolRank>
) {
    data class SchoolRank(
        val agencyCode: String,
        val logoImageUrl: String,
        val name: String,
        val walkCount: Int
    )

    data class MySchoolRank(
        val agencyCode: String,
        val logoImageUrl: String,
        val name: String,
        val rank: Int,
        val walkCount: Int
    )
}
