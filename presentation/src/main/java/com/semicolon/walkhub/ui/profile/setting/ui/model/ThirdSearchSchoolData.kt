package com.semicolon.walkhub.ui.profile.setting.ui.model

data class ThirdSearchSchoolData(
    val schoolList: List<SchoolInfo>
) {
    data class SchoolInfo(
        val schoolId: Int,
        val logoImageUrl: String,
        val schoolName: String
    )
}
