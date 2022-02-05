package com.semicolon.domain.entity.rank

class SearchSchoolEntity(
    val schoolList: List<SchoolInfo>
) {
    data class SchoolInfo(
        val agencyCode: String,
        val logoImageUrl: String,
        val rank: Int,
        val schoolName: String,
        val walkCount: Int
    )
}