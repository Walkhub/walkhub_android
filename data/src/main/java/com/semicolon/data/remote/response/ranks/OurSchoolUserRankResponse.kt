package com.semicolon.data.remote.response.ranks

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.rank.OurSchoolUserRankEntity

data class OurSchoolUserRankResponse(
    @SerializedName("my_ranking") val myRanking: Ranking?,
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

    fun Ranking.toEntity() =
        OurSchoolUserRankEntity.Ranking(
            classNum = classNum,
            grade = grade,
            name = name,
            profileImageUrl = profileImageUrl,
            ranking = ranking,
            userId = userId,
            walkCount = walkCount
        )
}

fun OurSchoolUserRankResponse.toEntity() =
    OurSchoolUserRankEntity(
        myRanking = myRanking?.toEntity(),
        rankingList = rankingList.map { it.toEntity() }
    )
