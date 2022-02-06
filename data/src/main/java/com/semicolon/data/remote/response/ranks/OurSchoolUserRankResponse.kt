package com.semicolon.data.remote.response.ranks

import com.google.gson.annotations.SerializedName

data class OurSchoolUserRankResponse(
    @SerializedName("my_ranking") val myRanking: Ranking,
    @SerializedName("ranking_list")val rankingList: List<Ranking>
)
{
    data class Ranking(
        @SerializedName("class_num") val classNum: Int,
        @SerializedName("grade") val grade: Int,
        @SerializedName("name") val name: String,
        @SerializedName("profile_image_url") val profileImageUrl: String,
        @SerializedName("ranking") val ranking: Int,
        @SerializedName("user_id") val userId: Int,
        @SerializedName("walk_count") val walkCount: Int
    )
}
