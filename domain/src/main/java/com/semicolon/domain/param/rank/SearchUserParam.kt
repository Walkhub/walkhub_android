package com.semicolon.domain.param.rank

import com.semicolon.domain.enums.MoreDateType

data class SearchUserParam(
    val schoolId: Int,
    val name: String,
    val moreDateType: MoreDateType
)
