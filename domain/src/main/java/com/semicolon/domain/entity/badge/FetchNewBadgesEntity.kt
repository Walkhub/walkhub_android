package com.semicolon.domain.entity.badge

data class FetchNewBadgesEntity(
    val badgeList: List<Badge>
) {
    data class Badge(
        val badgeId: Int,
        val badgeImageUrl: String,
        val badgeName: String
    )
}