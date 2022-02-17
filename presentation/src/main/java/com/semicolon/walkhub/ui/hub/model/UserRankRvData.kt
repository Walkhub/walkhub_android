package com.semicolon.walkhub.ui.hub.model

data class UserRankRvData(
    val profileUrl: String,
    val name: String,
    val walkCount: Int,
    val rank: Int
)

fun OurSchoolUserRankData.Ranking.toRvData() =
    UserRankRvData(
        profileUrl = profileImageUrl,
        name = name,
        walkCount = walkCount,
        rank = ranking
    )

fun UserRankData.UserRank.toRvData() =
    UserRankRvData(
        profileUrl = profileImageUrl,
        name = name,
        walkCount = walkCount,
        rank = ranking
    )