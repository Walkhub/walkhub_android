package com.semicolon.data.remote.response

import com.google.gson.annotations.SerializedName

data class CheckSchoolRank(
    @SerializedName("my_school_rank") val mySchoolRank: MySchoolRank,
    @SerializedName("school_list") val schoolRankList: List<SchoolRankList>
)