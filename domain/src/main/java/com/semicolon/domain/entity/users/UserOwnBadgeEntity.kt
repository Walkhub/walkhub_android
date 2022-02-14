package com.semicolon.domain.entity.users

data class UserOwnBadgeEntity(
    val badgeList: List<Badge>
) {
    data class Badge(
        val badgeId: Int,
        val badgeImage: String,
        val badgeName: String
    )
}