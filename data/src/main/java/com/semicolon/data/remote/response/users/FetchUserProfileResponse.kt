package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.users.UserProfileEntity

data class FetchUserProfileResponse(
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

fun FetchUserProfileResponse.TitleBadge.toEntity() =
    UserProfileEntity.TitleBadge(
        id = id,
        image = image,
        name = name
    )

fun FetchUserProfileResponse.toEntity() =
    UserProfileEntity(
        classRoom = classRoom,
        grade = grade,
        name = name,
        profileImage = profileImage,
        schoolName = schoolName,
        titleBadge = titleBadge.toEntity()
    )
