package com.semicolon.data.remote.request.users.inquirymypage

data class InquiryMypageResponse(
    val birthday: String,
    val `class`: Int,
    val grade: Int,
    val height: Double,
    val id: Int,
    val name: String,
    val profile_image: String,
    val school_name: String,
    val sex: String,
    val title_badge: TitleBadge,
    val weight: Int
)