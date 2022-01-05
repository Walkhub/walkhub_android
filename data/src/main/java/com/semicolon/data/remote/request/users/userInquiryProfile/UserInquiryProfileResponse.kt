package com.semicolon.data.remote.request.users.userInquiryProfile

data class UserInquiryProfileResponse(
    val `class`: Int,
    val grade: Int,
    val name: String,
    val profile_image: String,
    val school_name: String,
    val title_badge: TitleBadge
)