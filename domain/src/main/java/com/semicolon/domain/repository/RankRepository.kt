package com.semicolon.domain.repository

import com.semicolon.domain.entity.rank.*
import com.semicolon.domain.enums.DateType
import com.semicolon.domain.param.rank.FetchOurSchoolUserRankParam
import com.semicolon.domain.param.rank.FetchUserRankParam
import com.semicolon.domain.param.rank.SearchSchoolParam
import com.semicolon.domain.param.rank.SearchUserParam
import kotlinx.coroutines.flow.Flow

interface RankRepository {
    suspend fun fetchSchoolRank(dateType: DateType): Flow<SchoolRankEntity>

    suspend fun searchSchool(searchSchoolParam: SearchSchoolParam): Flow<SearchSchoolEntity>

    suspend fun fetchUserRank(fetchUserRankParam: FetchUserRankParam): Flow<UserRankEntity>

    suspend fun fetchOurSchoolUserRank(fetchOurSchoolUserRankParam: FetchOurSchoolUserRankParam): Flow<OurSchoolUserRankEntity>

    suspend fun searchUser(searchUserParam: SearchUserParam): Flow<SearchUserEntity>
}