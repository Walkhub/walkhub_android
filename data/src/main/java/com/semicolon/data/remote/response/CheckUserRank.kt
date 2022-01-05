package com.semicolon.data.remote.response

data class CheckUserRank(
    val my_rank: MyRank,
    val rank_list: List<Rank>
)