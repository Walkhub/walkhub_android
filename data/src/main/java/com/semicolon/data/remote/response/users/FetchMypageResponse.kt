package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName

data class FetchMypageResponse(
    @SerializedName("birthday") val birthday: String,
    @SerializedName("class") val classRoom: Int,
    @SerializedName("grade") val grade: Int,
    @SerializedName("height") val height: Double,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("profile_image") val profileImage: String,
    @SerializedName("school_name") val schoolName: String,
    @SerializedName("sex") val sex: String,
    @SerializedName("title_badge") val titleBadge: TitleBadge,
    @SerializedName("weight") val weight: Int
) {
    data class TitleBadge(
        @SerializedName("id") val id: Int,
        @SerializedName("image") val image: String,
        @SerializedName("name") val name: String
    )
}