package com.semicolon.domain.param.rank

import com.semicolon.domain.enum.DateType
import com.semicolon.domain.enum.RankScope

data class FetchUserRankParam(
    val agencyCode: String,
    val scope: RankScope,
    val dateType: DateType
)