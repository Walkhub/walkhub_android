package com.semicolon.walkhub.ui.hub.model

data class SearchSchoolData(
    val schoolList: List<SchoolInfo>
) {
    data class SchoolInfo(
        val schoolId: Int,
        val schoolName: String,
        val ranking: Int,
        val logoImageUrl: String,
        val walkCount: Int
    )
}