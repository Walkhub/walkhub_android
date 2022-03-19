package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.users.FetchInfoEntity

data class FetchInfoResponse(
    @SerializedName("name") val name: String,
    @SerializedName("profile_image_url") val profileImageUrl: String,
    @SerializedName("school_name") val schoolName: String,
    @SerializedName("grade") val grade: Int,
    @SerializedName("class_num") val classNum: Int
)

fun FetchInfoResponse.toEntity() =
    FetchInfoEntity(
        name = name,
        profileImageUrl = profileImageUrl,
        schoolName = schoolName,
        grade = grade,
        classNum = classNum
    )