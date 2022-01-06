package com.semicolon.data.remote.response

import com.google.gson.annotations.SerializedName

data class CheckUserRank(
    @SerializedName("my_rank") val myRank: MyRank,
    @SerializedName("rank_list") val userRankList: List<UserRankList>
)