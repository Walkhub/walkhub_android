package com.semicolon.domain.entity.rank

data class SearchUserEntity(
    val userList: List<UserInfo>
) {
    data class UserInfo(
        val classNum: Int,
        val grade: Int,
        val name: String,
        val profileImageUrl: String,
        val ranking: Int,
        val userId: Int,
        val walkCount: Int
    )
}