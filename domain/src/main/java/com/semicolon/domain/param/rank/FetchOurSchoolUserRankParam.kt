package com.semicolon.domain.param.rank

import com.semicolon.domain.enums.DateType
import com.semicolon.domain.enums.RankScope

data class FetchOurSchoolUserRankParam(
    val scope: RankScope,
    val dateType: DateType
)
