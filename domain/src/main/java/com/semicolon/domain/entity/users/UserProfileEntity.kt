package com.semicolon.domain.entity.users

data class UserProfileEntity(
    val classRoom: Int,
    val grade: Int,
    val name: String,
    val profileImage: String,
    val schoolName: String,
    val titleBadge: TitleBadge
) {
    data class TitleBadge(
        val badgeId: Int,
        val badgeImage: String,
        val badgeName: String
    )
}