package com.semicolon.data.remote.response.users.userinquiryprofile

import com.google.gson.annotations.SerializedName

data class UserInquiryProfileResponse(
    @SerializedName("class") val classRoom : Int,
    @SerializedName("grade") val grade: Int,
    @SerializedName("name") val name: String,
    @SerializedName("profile_image") val profileImage: String,
    @SerializedName("school_name") val schoolName: String,
    @SerializedName("title_badge") val titleBadge: TitleBadge
) {
    data class TitleBadge(
        @SerializedName("id") val id: Int,
        @SerializedName("image") val image: String,
        @SerializedName("name") val name: String
    )
}