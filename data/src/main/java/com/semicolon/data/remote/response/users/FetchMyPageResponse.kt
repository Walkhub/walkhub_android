package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.users.UserMyPageEntity

data class FetchMyPageResponse(
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
        @SerializedName("id") val badgeId: Int,
        @SerializedName("image") val badgeImage: String,
        @SerializedName("name") val badgeName: String
    )
}

fun FetchMyPageResponse.TitleBadge.toEntity() =
    UserMyPageEntity.TitleBadge(
        badgeId = badgeId,
        badgeImage = badgeImage,
        badgeName = badgeName
    )

fun FetchMyPageResponse.toEntity() =
    UserMyPageEntity(
        birthday = birthday,
        classRoom = classRoom,
        grade = grade,
        height = height,
        id = id,
        name = name,
        profileImage = profileImage,
        schoolName = schoolName,
        sex = sex,
        titleBadge = titleBadge.toEntity(),
        weight = weight
    )
