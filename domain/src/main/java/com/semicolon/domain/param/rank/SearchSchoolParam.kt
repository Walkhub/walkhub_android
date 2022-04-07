package com.semicolon.domain.param.rank

import com.semicolon.domain.enums.DateType
import com.semicolon.domain.enums.RankSort
import com.semicolon.domain.enums.TheRankScope

data class SearchSchoolParam (
    val name: String,
    val sort: RankSort,
    val scope: TheRankScope,
    val dateType: DateType
)