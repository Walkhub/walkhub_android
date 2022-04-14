package com.semicolon.domain.entity.school

data class SearchSchoolEntity(
    val schoolList: List<SchoolInfo>
) {
    data class SchoolInfo(
        val schoolId: Int,
        val logoImageUrl: String,
        val schoolName: String
    )
}
