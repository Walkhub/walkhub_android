package com.semicolon.domain.param.rank

import com.semicolon.domain.enum.MoreDateType
import com.semicolon.domain.enum.RankScope

data class SearchUserParam(
    val schoolId: Int,
    val name: String,
    val dateType: MoreDateType
)
