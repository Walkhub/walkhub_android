package com.semicolon.data.remote.response.ranks.search.school

import com.google.gson.annotations.SerializedName

//search school
data class SchoolInfo(
    @SerializedName("agency_code") val agencyCode: String,
    @SerializedName("logo_image_url") val logoImageUrl: String,
    val name: String,
    val rank: Int,
    @SerializedName("walk_count") val walkCount: Int
)