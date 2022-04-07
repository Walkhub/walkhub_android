package com.semicolon.data.remote.response.ranks.inquiryRank.school

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.rank.SchoolRankEntity

data class SchoolRankResponse(
    @SerializedName("my_school_rank") val mySchoolRank: MySchoolRank
) {
    data class MySchoolRank(
        @SerializedName("school_id") val schoolId: Int,
        @SerializedName("name") val name: String,
        @SerializedName("logo_image_url") val logoImageUrl: String,
        @SerializedName("grade") val grade: Int,
        @SerializedName("class_num") val classNum: Int
    )

    fun MySchoolRank.toEntity() =
        SchoolRankEntity.MySchoolRank(
            schoolId = schoolId,
            name = name,
            logoImageUrl = logoImageUrl,
            grade = grade,
            classNum = classNum
        )

}

fun SchoolRankResponse.toEntity() =
    SchoolRankEntity(
        mySchoolRank = mySchoolRank.toEntity()
    )