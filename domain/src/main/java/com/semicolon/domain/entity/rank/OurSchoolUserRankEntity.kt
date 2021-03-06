package com.semicolon.domain.entity.rank

data class OurSchoolUserRankEntity(
    val isJoinedClass: Boolean,
    val myRanking: Ranking?,
    val rankingList: List<Ranking>
) {
    data class Ranking(
        val name: String,
        val profileImageUrl: String?,
        val ranking: Int,
        val userId: Int,
        val walkCount: Int,
        val isMeasuring: Boolean
    )
}