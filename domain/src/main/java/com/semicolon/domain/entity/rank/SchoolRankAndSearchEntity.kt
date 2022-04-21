package com.semicolon.domain.entity.rank

data class SchoolRankAndSearchEntity(
    val schoolRankList: List<SchoolRank>
) {
    data class SchoolRank(
        val schoolId: Int,
        val schoolName: String,
        val ranking: Int,
        val logoImageUrl: String,
        val walkCount: Int,
        val userCount: Int
    )
}