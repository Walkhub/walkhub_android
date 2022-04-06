package com.semicolon.domain.entity.users

data class UserProfileEntity(
    val userId: Int,
    val name: String,
    val profileImageUrl: String,
    val schoolName: String,
    val schoolImageUrl: String,
    val grade: Int,
    val classNum: Int,
    val dailyWalkCountGoal: Int,
    val titleBadge: TitleBadge,
    val level: Level
) {
    data class TitleBadge(
        val badgeId: Int,
        val badgeName: String,
        val badgeImageUrl: String
    )

    data class Level(
        val levelName: String,
        val levelImageUrl: String
    )
}