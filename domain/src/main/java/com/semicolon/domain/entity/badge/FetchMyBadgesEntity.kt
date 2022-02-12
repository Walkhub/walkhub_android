package com.semicolon.domain.entity.badge

data class FetchMyBadgesEntity(
    val badgeList: List<Badge>
) {
    data class Badge(
        val badgeId: Int,
        val badgeImageUrl: String,
        val badgeName: String,
        val mine: Boolean
    )
}