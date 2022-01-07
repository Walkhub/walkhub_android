package com.semicolon.data.remote.response.ranks.inquiryRank.school

import com.google.gson.annotations.SerializedName

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
}