package com.semicolon.walkhub.ui.hub.model

import com.semicolon.domain.entity.rank.UserRankEntity

data class SchoolUserRankData(
    val rankList: List<UserRank>
) {

    data class UserRank(
        val userId: Int,
        val name: String,
        val ranking: Int,
        val profileImageUrl: String?,
        val walkCount: Int
    )
}

fun UserRankEntity.UserRank.toData() =
    SchoolUserRankData.UserRank(
        userId = userId,
        name = name,
        ranking = ranking,
        profileImageUrl = profileImageUrl,
        walkCount = walkCount
    )

fun UserRankEntity.toData() =
    SchoolUserRankData(
        rankList.map { it.toData() }
    )