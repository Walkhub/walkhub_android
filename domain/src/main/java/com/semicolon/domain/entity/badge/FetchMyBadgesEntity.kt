package com.semicolon.domain.entity.badge

data class FetchMyBadgesEntity(
    val badgeList: List<Badge>
) {
    data class Badge(
        val badgeId: Int,
        val name: String,
        val imageUrl: String,
        val mine: Boolean,
        val condition: String
    )
}