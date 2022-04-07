package com.semicolon.domain.entity.rank

data class SearchSchoolEntity(
    val schoolList: List<SchoolInfo>
) {
    data class SchoolInfo(
        val schoolId: Int,
        val schoolName: String,
        val ranking: Int,
        val logoImageUrl: String,
        val walkCount: Int,
        val userCount: Int
    )
}