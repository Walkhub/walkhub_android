package com.semicolon.domain.param.rank

import com.semicolon.domain.enum.MoreDateType

data class FetchUserRankParam(
    val schoolId: Int,
    val dateType: MoreDateType
)