package com.semicolon.domain.repository

import com.semicolon.domain.entity.rank.*
import com.semicolon.domain.param.rank.FetchOurSchoolUserRankParam
import com.semicolon.domain.param.rank.FetchUserRankParam
import com.semicolon.domain.param.rank.SearchUserParam
import com.semicolon.domain.param.user.FetchSchoolRankAndSearchParam
import kotlinx.coroutines.flow.Flow

interface RankRepository {
    suspend fun fetchMySchoolRank(): Flow<FetchMySchoolRankEntity>

    suspend fun fetchSchoolRankAndSearch(fetchSchoolRankAndSearchParam: FetchSchoolRankAndSearchParam): Flow<SchoolRankAndSearchEntity>

    suspend fun fetchUserRank(fetchUserRankParam: FetchUserRankParam): Flow<UserRankEntity>

    suspend fun fetchOurSchoolUserRank(fetchOurSchoolUserRankParam: FetchOurSchoolUserRankParam): Flow<OurSchoolUserRankEntity>

    suspend fun searchUser(searchUserParam: SearchUserParam): Flow<SearchUserEntity>
}