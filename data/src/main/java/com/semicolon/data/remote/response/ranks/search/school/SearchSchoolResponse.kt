package com.semicolon.data.remote.response.ranks.search.school

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.rank.SearchSchoolEntity

data class SearchSchoolResponse(
    @SerializedName("school_list") val schoolList: List<SchoolInfo>
) {
    data class SchoolInfo(
        @SerializedName("agency_code") val agencyCode: String,
        @SerializedName("logo_image_url") val logoImageUrl: String,
        @SerializedName("rank") val rank: Int,
        @SerializedName("school_name") val schoolName: String,
        @SerializedName("walk_count") val walkCount: Int
    )

    fun SchoolInfo.toEntity() =
        SearchSchoolEntity.SchoolInfo(
            agencyCode = agencyCode,
            logoImageUrl = logoImageUrl,
            rank = rank,
            schoolName = schoolName,
            walkCount = walkCount
        )
}

fun SearchSchoolResponse.toEntity() =
    SearchSchoolEntity(
        schoolList = schoolList.map { it.toEntity() }
    )