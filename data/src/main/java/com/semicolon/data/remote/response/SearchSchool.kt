package com.semicolon.data.remote.response

import com.google.gson.annotations.SerializedName

data class SearchSchool(
    @SerializedName("school_list") val school: List<SchoolList>
)