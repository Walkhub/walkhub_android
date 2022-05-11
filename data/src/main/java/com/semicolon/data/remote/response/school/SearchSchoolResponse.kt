package com.semicolon.data.remote.response.school

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.school.SearchSchoolEntity

data class SearchSchoolResponse(
    @SerializedName("search_school_list") val schoolList: List<SchoolInfo>
) {
    data class SchoolInfo(
        @SerializedName("school_id") val agencyCode: Long,
        @SerializedName("school_name") val schoolName: String,
        @SerializedName("logo_image_url") val logoImageUrl: String,
    )
}

fun SearchSchoolResponse.SchoolInfo.toEntity() =
    SearchSchoolEntity.SchoolInfo(
        schoolId = agencyCode,
        schoolName = schoolName,
        logoImageUrl = logoImageUrl
    )

fun SearchSchoolResponse.toEntity() =
    SearchSchoolEntity(
        schoolList.map { it.toEntity() }
    )
