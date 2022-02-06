package com.semicolon.domain.param.rank

import com.semicolon.domain.enum.DateType
import com.semicolon.domain.enum.RankScope

data class FetchOurSchoolUserRankParam(
    val scope: RankScope,
    val dateType: DateType
)
