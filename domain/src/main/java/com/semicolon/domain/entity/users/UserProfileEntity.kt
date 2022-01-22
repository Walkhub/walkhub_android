package com.semicolon.domain.entity.users
data class UserProfileEntity(
    val classRoom : Int,
    val grade: Int,
    val name: String,
    val profileImage: String,
    val schoolName: String,
    val titleBadge: TitleBadge
) {
    data class TitleBadge(
        val id: Int,
        val image: String,
        val name: String
    )
}