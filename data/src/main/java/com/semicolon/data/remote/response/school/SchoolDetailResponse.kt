package com.semicolon.data.remote.response.school

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.school.SchoolDetailEntity

data class SchoolDetailResponse(
    @SerializedName("week") val week: Week,
    @SerializedName("month") val month: Month
) {
    data class Week(
        @SerializedName("total_walk_count") val totalWalkCount: Int,
        @SerializedName("date") val date: String,
        @SerializedName("total_user_count") val totalUserCount: Int,
        @SerializedName("ranking") val ranking: Int
    )
    data class Month(
        @SerializedName("total_walk_count") val totalWalkCount: Int,
        @SerializedName("date") val date: String,
        @SerializedName("total_user_count") val totalUserCount: Int,
        @SerializedName("ranking") val ranking: Int
    )
}

fun SchoolDetailResponse.Week.toEntity() =
    SchoolDetailEntity.Week(
        totalWalkCount = totalWalkCount,
        date = date,
        totalUserCount = totalUserCount,
        ranking = ranking
    )

fun SchoolDetailResponse.Month.toEntity() =
    SchoolDetailEntity.Month(
        totalWalkCount = totalWalkCount,
        date = date,
        totalUserCount = totalUserCount,
        ranking = ranking
    )
    
fun SchoolDetailResponse.toEntity() =
    SchoolDetailEntity(
        week = week.toEntity(),
        month = month.toEntity()
    )
