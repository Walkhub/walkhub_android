package com.semicolon.domain.entity.badge

data class FetchMyBadgesEntity(
    val badgeList: List<Badge>
) {
    data class Badge(
        val badgeId: Int,
        val badgeName: String,
        val badgeImageUrl: String,
        val mine: Boolean,
        val condition: String
    )
}