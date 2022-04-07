package com.semicolon.domain.entity.school

data class SchoolDetailEntity(
    val totalUserCount: Int,
    val weekTotalWalkCount: Int,
    val monthTotalWalkCount: Int,
    val weekRanking: Int,
    val monthRanking: Int
)
