package com.semicolon.walkhub.ui.hub.model

data class HubSchoolRankData(
    val mySchoolRank: MySchool,
    val schoolList: List<OtherSchool>
) {

    data class MySchool(
        val schoolId: Int,
        val name: String,
        val logoImageUrl: String,
        val walkCount: Int,
        val grade: Int,
        val classNum: Int
    )

    data class OtherSchool(
        val schoolId: Int,
        val name: String,
        val ranking: Int,
        val studentCount: Int,
        val logoImageUrl: String,
        val walkCount: Int
    )
}