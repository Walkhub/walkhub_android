package com.semicolon.domain.param.rank

import com.semicolon.domain.enum.MoreDateType
import com.semicolon.domain.enum.RankScope

data class SearchUserParam(
    val name: String,
    val scope: RankScope,
    val moreDateType: MoreDateType,
    val grade: Int,
    val classNum: Int
)
