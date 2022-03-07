package com.semicolon.domain.param.rank

import com.semicolon.domain.enum.MoreDateType
import com.semicolon.domain.enum.RankScope

data class FetchOurSchoolUserRankParam(
    val scope: RankScope,
    val dateType: MoreDateType
)
