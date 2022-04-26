package com.semicolon.data.remote.response.ranks.inquiryRank.school

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.rank.SchoolRankAndSearchEntity

data class SchoolRankAndSearchResponse(
    @SerializedName("school_list") val schoolRankList: List<SchoolRank>
) {
    data class SchoolRank(
        @SerializedName("school_id") val schoolId: Int,
        @SerializedName("school_name") val schoolName: String,
        @SerializedName("ranking") val ranking: Int,
        @SerializedName("logo_image_url") val logoImageUrl: String,
        @SerializedName("walk_count") val walkCount: Int,
        @SerializedName("user_count") val userCount: Int
    )


    fun SchoolRank.toEntity() =
        SchoolRankAndSearchEntity.SchoolRank(
            schoolId = schoolId,
            schoolName = schoolName,
            ranking = ranking,
            logoImageUrl = logoImageUrl,
            walkCount = walkCount,
            userCount = userCount
        )
}

fun SchoolRankAndSearchResponse.toEntity() =
    SchoolRankAndSearchEntity(
        schoolRankList = schoolRankList.map { it.toEntity() }
    )