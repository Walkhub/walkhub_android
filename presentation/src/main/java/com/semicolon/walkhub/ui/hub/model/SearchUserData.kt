package com.semicolon.walkhub.ui.hub.model

data class SearchUserData(
    val userList: List<UserInfo>
) {
    data class UserInfo(
        val profileUrl: String?,
        val name: String,
        val walkCount: Int,
        val rank: Int
    )
}