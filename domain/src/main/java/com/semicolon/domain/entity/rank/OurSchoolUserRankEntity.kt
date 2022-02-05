package com.semicolon.domain.entity.rank

data class OurSchoolUserRankEntity(
    val myRanking: MyRanking,
    val rankingList: List<Ranking>
) {
    data class MyRanking(
        val classNum: Int,
        val grade: Int,
        val name: String,
        val profileImageUrl: String,
        val ranking: Int,
        val userId: Int,
        val walkCount: Int
    )

    data class Ranking(
        val classNum: Int,
        val grade: Int,
        val name: String,
        val profileImageUrl: String,
        val ranking: Int,
        val userId: Int,
        val walkCount: Int
    )
}