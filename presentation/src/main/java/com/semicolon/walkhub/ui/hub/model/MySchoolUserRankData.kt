package com.semicolon.walkhub.ui.hub.model

import com.semicolon.domain.entity.rank.OurSchoolUserRankEntity

data class MySchoolUserRankData(
    val isJoinedClass: Boolean,
    val myRanking: Ranking?,
    val rankingList: List<Ranking>
) {

    data class Ranking(
        val name: String,
        val profileImageUrl: String?,
        val ranking: Int,
        val userId: Int,
        val walkCount: Int
    )
}

fun OurSchoolUserRankEntity.toData() =
    rankingList.map { it.toData() }?.let {
        MySchoolUserRankData(
            isJoinedClass = isJoinedClass,
            myRanking = myRanking?.toData(),
            rankingList = it as List<MySchoolUserRankData.Ranking>
        )
    }

fun OurSchoolUserRankEntity.Ranking.toData() =
        MySchoolUserRankData.Ranking(
            name = name,
            profileImageUrl = profileImageUrl,
            ranking = ranking,
            userId = userId,
            walkCount = walkCount
        )