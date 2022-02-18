package com.semicolon.domain.param.rank

import com.semicolon.domain.enum.MoreDateType
import com.semicolon.domain.enum.RankScope

data class FetchUserRankParam(
    val schoolId: Int,
    val scope: RankScope,
    val moreDateType: MoreDateType
)