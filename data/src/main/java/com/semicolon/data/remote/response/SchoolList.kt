package com.semicolon.data.remote.response

import com.google.gson.annotations.SerializedName

//search school
data class SchoolList(
    @SerializedName("agency_code") val agencyCode: String,
    @SerializedName("logo_image_url") val logoImageUrl: String,
    @SerializedName("name") val name: String,
    @SerializedName("rank") val rank: Int,
    @SerializedName("walk_count") val walkCount: Int
)