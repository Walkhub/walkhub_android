package com.semicolon.walkhub.ui.hub.model

import com.semicolon.domain.entity.rank.OurSchoolUserRankEntity

data class MySchoolUserRankData(
    val myRanking: Ranking,
    val rankingList: List<Ranking>
) {

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

fun OurSchoolUserRankEntity.toData() =
    MySchoolUserRankData(
        myRanking = myRanking.toData(),
        rankingList = rankingList.map { it.toData() }
    )

fun OurSchoolUserRankEntity.Ranking.toData() =
    MySchoolUserRankData.Ranking(
        classNum = classNum,
        grade = grade,
        name = name,
        profileImageUrl = profileImageUrl,
        ranking = ranking,
        userId = userId,
        walkCount = walkCount
    )