package com.semicolon.domain.entity.users

data class UserOwnBadgeEntity(
    val badgeList: List<Badge>
) {
    data class Badge(
        val id: Int,
        val image: String,
        val name: String
    )
}