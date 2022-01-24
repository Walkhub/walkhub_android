package com.semicolon.data.remote.response.school

import com.google.gson.annotations.SerializedName
import com.semicolon.data.remote.response.ranks.search.school.SearchSchoolResponse

data class SearchSchoolResponse(
    @SerializedName("school_list") val schoolList: List<SchoolInfo>

) {
    data class SchoolInfo(
        @SerializedName("agency_code") val agencyCode: String,
        @SerializedName("name") val name: String,
        @SerializedName("rank") val rank: Int,
        @SerializedName("logo_image_url") val logoImageUrl: String,
        @SerializedName("walk_count") val walkCount: Int
    )
}