package com.semicolon.domain.entity.rank

data class UserRankEntity(
    val rankList: List<UserRank>
) {
    data class UserRank(
        val userId: Int,
        val name: String,
        val grade: Int,
        val classNum: Int,
        val number: Int,
        val ranking: Int,
        val profileImageUrl: String,
        val walkCount: Int
    )
}