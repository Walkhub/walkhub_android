package com.semicolon.walkhub.ui.hub.model

data class UserRankRvData(
    val profileUrl: String,
    val name: String,
    val walkCount: Int,
    val rank: Int
)

fun MySchoolUserRankData.Ranking.toRvData() =
    UserRankRvData(
        profileUrl = profileImageUrl,
        name = name,
        walkCount = walkCount,
        rank = ranking
    )

fun SchoolUserRankData.UserRank.toRvData() =
    UserRankRvData(
        profileUrl = profileImageUrl,
        name = name,
        walkCount = walkCount,
        rank = ranking
    )