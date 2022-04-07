package com.semicolon.data.remote.response.school

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.school.SchoolDetailEntity

data class SchoolDetailResponse(
    @SerializedName("total_user_count") val totalUserCount: Int,
    @SerializedName("week_total_walk_count") val weekTotalWalkCount: Int,
    @SerializedName("month_total_walk_count") val monthTotalWalkCount: Int,
    @SerializedName("week_ranking") val weekRanking: Int,
    @SerializedName("month_ranking") val monthRanking: Int
)

fun SchoolDetailResponse.toEntity() =
    SchoolDetailEntity(
        totalUserCount = totalUserCount,
        weekTotalWalkCount = weekTotalWalkCount,
        monthTotalWalkCount = monthTotalWalkCount,
        weekRanking = weekRanking,
        monthRanking = monthRanking
    )
