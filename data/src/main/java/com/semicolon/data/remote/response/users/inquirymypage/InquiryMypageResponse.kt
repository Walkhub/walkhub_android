package com.semicolon.data.remote.response.users.inquirymypage

import com.google.gson.annotations.SerializedName

data class InquiryMypageResponse(
    @SerializedName("birthday") val birthday: String,
    @SerializedName("class") val `class`: Int,
    @SerializedName("grade") val grade: Int,
    @SerializedName("height") val height: Double,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("profile_image") val profileImage: String,
    @SerializedName("school_name") val schoolName: String,
    @SerializedName("sex") val sex: String,
    @SerializedName("title_badge") val titleBadge: TitleBadge,
    @SerializedName("weight") val weight: Int
)