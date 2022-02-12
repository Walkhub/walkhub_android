package com.semicolon.data.remote.response.ranks.inquiryRank.school

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.rank.SchoolRankEntity

data class SchoolRankResponse(
    @SerializedName("my_school_rank") val mySchoolRank: MySchoolRank,
    @SerializedName("school_list") val schoolRankList: List<SchoolRank>
) {
    data class SchoolRank(
        @SerializedName("agency_code") val agencyCode: String,
        @SerializedName("logo_image_url") val logoImageUrl: String,
        @SerializedName("name") val name: String,
        @SerializedName("walk_count") val walkCount: Int
    )

    data class MySchoolRank(
        @SerializedName("agency_code") val agencyCode: String,
        @SerializedName("logo_image_url") val logoImageUrl: String,
        @SerializedName("name") val name: String,
        @SerializedName("rank") val rank: Int,
        @SerializedName("walk_count") val walkCount: Int
    )

    fun SchoolRank.toEntity() =
        SchoolRankEntity.SchoolRank(
            agencyCode = agencyCode,
            logoImageUrl = logoImageUrl,
            name = name,
            walkCount = walkCount
        )

    fun MySchoolRank.toEntity() =
        SchoolRankEntity.MySchoolRank(
            agencyCode = agencyCode,
            logoImageUrl = logoImageUrl,
            name = name,
            rank = rank,
            walkCount = walkCount
        )

}

fun SchoolRankResponse.toEntity() =
    SchoolRankEntity(
        mySchoolRank = mySchoolRank.toEntity(),
        schoolRankList = schoolRankList.map { it.toEntity() }
    )