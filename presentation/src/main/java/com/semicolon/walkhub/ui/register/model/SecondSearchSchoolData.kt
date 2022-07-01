package com.semicolon.walkhub.ui.register.model

data class SecondSearchSchoolData(
    val schoolList: List<SchoolInfo>
) {
    data class SchoolInfo(
        val schoolId: Long,
        val logoImageUrl: String,
        val schoolName: String
    )
}
