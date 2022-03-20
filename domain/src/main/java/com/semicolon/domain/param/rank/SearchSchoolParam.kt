package com.semicolon.domain.param.rank

import com.semicolon.domain.enums.DateType

data class SearchSchoolParam (
    val name: String,
    val dateType: DateType
)