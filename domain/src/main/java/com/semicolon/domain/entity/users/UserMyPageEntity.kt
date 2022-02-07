package com.semicolon.domain.entity.users

data class UserMyPageEntity(
    val birthday: String,
    val classRoom : Int,
    val grade: Int,
    val height: Double,
    val id: Int,
    val name: String,
    val profileImage: String,
    val schoolName: String,
    val sex: String,
    val titleBadge: TitleBadge,
    val weight: Int
) {
    data class TitleBadge(
        val badgeId: Int,
        val badgeImage: String,
        val badgeName: String
    )
}