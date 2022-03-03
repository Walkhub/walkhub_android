package com.semicolon.domain.param.rank

import com.semicolon.domain.enums.DateType

data class FetchUserRankParam(
    val schoolId: Int,
    val dateType: DateType
)