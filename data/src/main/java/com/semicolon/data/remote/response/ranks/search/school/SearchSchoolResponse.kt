package com.semicolon.data.remote.response.ranks.search.school

import com.google.gson.annotations.SerializedName

data class SearchSchoolResponse(
    @SerializedName("school_list") val school: List<SchoolInfo>
)