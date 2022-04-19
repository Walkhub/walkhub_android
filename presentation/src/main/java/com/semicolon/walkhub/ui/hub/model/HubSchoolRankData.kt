package com.semicolon.walkhub.ui.hub.model

data class HubSchoolRankData(
    val schoolList: List<OtherSchool>
) {
    data class OtherSchool(
        val schoolId: Int,
        val schoolName: String,
        val ranking: Int,
        val logoImageUrl: String,
        val walkCount: Int,
        val userCount: Int
    )
}