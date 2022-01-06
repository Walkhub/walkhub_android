package com.semicolon.data.remote.response.ranks.inquiryRank.user

import com.google.gson.annotations.SerializedName
import com.semicolon.data.remote.response.ranks.inquiryRank.user.MyRank
import com.semicolon.data.remote.response.ranks.inquiryRank.user.UserRank

data class UserRankResponse(
    @SerializedName("my_rank") val myRank: MyRank,
    @SerializedName("rank_list") val userRank: List<UserRank>
)