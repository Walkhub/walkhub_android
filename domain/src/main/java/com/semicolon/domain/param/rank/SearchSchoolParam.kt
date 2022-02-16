package com.semicolon.domain.param.rank

import com.semicolon.domain.enum.MoreDateType

data class SearchSchoolParam (
    val name: String,
    val moreDateType: MoreDateType
)