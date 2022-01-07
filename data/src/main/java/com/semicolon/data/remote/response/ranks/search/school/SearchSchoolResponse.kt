package com.semicolon.data.remote.response.ranks.search.school

import com.google.gson.annotations.SerializedName

data class SearchSchoolResponse(
    @SerializedName("school_list") val school: List<SchoolInfo>
) {
    data class SchoolInfo(
        @SerializedName("agency_code") val agencyCode: String,
        @SerializedName("logo_image_url") val logoImageUrl: String,
        @SerializedName("name") val name: String,
        @SerializedName("rank") val rank: Int,
        @SerializedName("walk_count") val walkCount: Int
    )
}