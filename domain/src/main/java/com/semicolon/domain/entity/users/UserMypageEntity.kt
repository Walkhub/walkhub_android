package com.semicolon.domain.entity.users

data class UserMypageEntity(
    val birthday: String,
    val `class`: Int,
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
        val id: Int,
        val image: String,
        val name: String
    )
}