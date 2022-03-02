package com.semicolon.domain.param.rank

import com.semicolon.domain.enums.MoreDateType

data class SearchSchoolParam (
    val name: String,
    val moreDateType: MoreDateType
)