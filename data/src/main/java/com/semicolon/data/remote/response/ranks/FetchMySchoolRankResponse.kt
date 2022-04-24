package com.semicolon.data.remote.response.ranks

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.rank.FetchMySchoolRankEntity

data class FetchMySchoolRankResponse(
    @SerializedName("school_id") val schoolId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("logo_image_url") val logoImageUrl: String,
    @SerializedName("grade") val grade: Int?,
    @SerializedName("class_num") val classNum: Int?
)


fun FetchMySchoolRankResponse.toEntity() =
    FetchMySchoolRankEntity(
        schoolId = schoolId,
        name = name,
        logoImageUrl = logoImageUrl,
        grade = grade,
        classNum = classNum
    )