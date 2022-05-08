package com.semicolon.data.remote.response.ranks

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.rank.OurSchoolUserRankEntity

data class OurSchoolUserRankResponse(
    @SerializedName("is_joined_class") val isJoinedClass: Boolean,
    @SerializedName("my_ranking") val myRanking: Ranking,
    @SerializedName("rank_list") val rankingList: List<Ranking>
) {
    data class Ranking(
        @SerializedName("name") val name: String,
        @SerializedName("profile_image_url") val profileImageUrl: String?,
        @SerializedName("ranking") val ranking: Int,
        @SerializedName("user_id") val userId: Int,
        @SerializedName("walk_count") val walkCount: Int,
        @SerializedName("is_measuring") val isMeasuring: Boolean
    )
}

fun OurSchoolUserRankResponse.Ranking.toEntity() =
    OurSchoolUserRankEntity.Ranking(
        name = name,
        profileImageUrl = profileImageUrl,
        ranking = ranking,
        userId = userId,
        walkCount = walkCount,
        isMeasuring = isMeasuring
    )

fun OurSchoolUserRankResponse.toEntity() =
    OurSchoolUserRankEntity(
        isJoinedClass = isJoinedClass,
        myRanking = myRanking.toEntity(),
        rankingList = rankingList.map { it.toEntity() }
    )
