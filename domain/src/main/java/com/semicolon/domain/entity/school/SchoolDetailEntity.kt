package com.semicolon.domain.entity.school

data class SchoolDetailEntity(
    val week: Week,
    val month: Month
) {
    data class Week(
        val totalWalkCount: Int,
        val date: String,
        val totalUserCount: Int,
        val ranking: Int
    )
    data class Month(
        val totalWalkCount: Int,
        val date: String,
        val totalUserCount: Int,
        val ranking: Int
    )
}