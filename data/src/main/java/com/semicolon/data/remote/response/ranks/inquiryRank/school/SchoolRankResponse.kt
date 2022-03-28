package com.semicolon.data.remote.response.ranks.inquiryRank.school

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.rank.SchoolRankEntity

data class SchoolRankResponse(
    @SerializedName("my_school_rank") val mySchoolRank: MySchoolRank,
    @SerializedName("school_list") val schoolRankList: List<SchoolRank>
) {
    data class SchoolRank(
        @SerializedName("school_id") val schoolId: Int,
        @SerializedName("name") val name: String,
        @SerializedName("ranking") val ranking: Int,
        @SerializedName("student_count") val studentCount: Int,
        @SerializedName("logo_image_url") val logoImageUrl: String,
        @SerializedName("walk_count") val walkCount: Int
    )

    data class MySchoolRank(
        @SerializedName("school_id") val schoolId: Int,
        @SerializedName("name") val name: String,
        @SerializedName("logo_image_url") val logoImageUrl: String,
        @SerializedName("grade") val grade: Int,
        @SerializedName("class_num") val classNum: Int
    )

    fun SchoolRank.toEntity() =
        SchoolRankEntity.SchoolRank(
            schoolId = schoolId,
            name = name,
            ranking = ranking,
            studentCount = studentCount,
            logoImageUrl = logoImageUrl,
            walkCount = walkCount
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
        mySchoolRank = mySchoolRank.toEntity(),
        schoolRankList = schoolRankList.map { it.toEntity() }
    )