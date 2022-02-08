package com.semicolon.domain.entity.rank

data class UserRankEntity(
    val rankList: List<UserRank>
) {
    data class UserRank(
        val classNum: Int,
        val grade: Int,
        val name: String,
        val profileImageUrl: String,
        val rank: Int,
        val userId: Int,
        val walkCount: Int
    )
}