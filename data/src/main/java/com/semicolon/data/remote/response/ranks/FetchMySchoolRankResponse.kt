package com.semicolon.data.remote.response.ranks

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.rank.FetchMySchoolRankEntity

data class FetchMySchoolRankResponse(
    @SerializedName("my_school_rank") val mySchoolRank: MySchoolRank
) {
    data class MySchoolRank(
        @SerializedName("school_id") val schoolId: Int,
        @SerializedName("name") val name: String,
        @SerializedName("logo_image_url") val logoImageUrl: String,
        @SerializedName("grade") val grade: Int?,
        @SerializedName("class_num") val classNum: Int?
    )
}

fun FetchMySchoolRankResponse.toEntity() =
    FetchMySchoolRankEntity(
        mySchoolRank = mySchoolRank.toEntity()
    )

fun FetchMySchoolRankResponse.MySchoolRank.toEntity() =
    FetchMySchoolRankEntity.MySchoolRank(
        schoolId = schoolId,
        name = name,
        logoImageUrl = logoImageUrl,
        grade = grade,
        classNum = classNum
    )