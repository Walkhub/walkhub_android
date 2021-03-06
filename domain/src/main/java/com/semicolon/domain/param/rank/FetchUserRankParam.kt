package com.semicolon.domain.param.rank

import com.semicolon.domain.enums.MoreDateType

data class FetchUserRankParam(
    val schoolId: Int,
    val dateType: MoreDateType
)