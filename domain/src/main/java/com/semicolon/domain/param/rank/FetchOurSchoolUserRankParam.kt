package com.semicolon.domain.param.rank

import com.semicolon.domain.enums.MoreDateType
import com.semicolon.domain.enums.RankScope

data class FetchOurSchoolUserRankParam(
    val scope: RankScope,
    val dateType: MoreDateType
)
