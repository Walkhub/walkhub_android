package com.semicolon.data.remote.response.school

import com.google.gson.annotations.SerializedName

data class SearchSchoolResponse(
    @SerializedName("school_list") val schoolList: List<SchoolInfo>
) {
    data class SchoolInfo(
        @SerializedName("agency_code") val agencyCode: String,
        @SerializedName("name") val name: String,
        @SerializedName("logo_image_url") val logoImageUrl: String,
    )
}