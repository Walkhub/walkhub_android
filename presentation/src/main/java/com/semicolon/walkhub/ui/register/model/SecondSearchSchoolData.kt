package com.semicolon.walkhub.ui.register.model

data class SecondSearchSchoolData(
    val schoolList: List<SchoolInfo>
) {
    data class SchoolInfo(
        val SchoolProfile: String,
        val SchoolName: String
    )
}
