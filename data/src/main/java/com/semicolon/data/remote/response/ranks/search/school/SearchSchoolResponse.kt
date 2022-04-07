package com.semicolon.data.remote.response.ranks.search.school

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.rank.SearchSchoolEntity

data class SearchSchoolResponse(
    @SerializedName("school_list") val schoolList: List<SchoolInfo>
) {
    data class SchoolInfo(
        @SerializedName("school_id") val schoolId: Int,
        @SerializedName("school_name") val schoolName: String,
        @SerializedName("ranking") val ranking: Int,
        @SerializedName("logo_image_url") val logoImageUrl: String,
        @SerializedName("walk_count") val walkCount: Int,
        @SerializedName("user_count") val userCount: Int
    )

    fun SchoolInfo.toEntity() =
        SearchSchoolEntity.SchoolInfo(
            schoolId = schoolId,
            schoolName = schoolName,
            ranking = ranking,
            logoImageUrl = logoImageUrl,
            walkCount = walkCount,
            userCount = userCount
        )
}

fun SearchSchoolResponse.toEntity() =
    SearchSchoolEntity(
        schoolList = schoolList.map { it.toEntity() }
    )