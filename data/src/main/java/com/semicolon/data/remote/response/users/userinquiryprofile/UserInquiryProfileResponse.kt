package com.semicolon.data.remote.response.users.userinquiryprofile

import com.google.gson.annotations.SerializedName

data class UserInquiryProfileResponse(
    @SerializedName("class") val `class`: Int,
    @SerializedName("grade") val grade: Int,
    @SerializedName("name") val name: String,
    @SerializedName("profile_image") val profileImage: String,
    @SerializedName("school_name") val schoolName: String,
    @SerializedName("title_badge") val titleBadge: TitleBadge
)