package com.semicolon.domain.param.rank

import com.semicolon.domain.enum.DateType

data class FetchUserRankParam(
    val schoolId: Int,
    val dateType: DateType
)