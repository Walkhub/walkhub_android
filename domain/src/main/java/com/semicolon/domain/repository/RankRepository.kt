package com.semicolon.domain.repository

import com.semicolon.domain.entity.rank.OurSchoolUserRankEntity
import com.semicolon.domain.entity.rank.SchoolRankEntity
import com.semicolon.domain.entity.rank.SearchSchoolEntity
import com.semicolon.domain.entity.rank.UserRankEntity
import kotlinx.coroutines.flow.Flow

interface RankRepository {
    suspend fun fetchSchoolRank(dateType: String): Flow<SchoolRankEntity>

    suspend fun searchSchool(name: String): Flow<SearchSchoolEntity>

    suspend fun fetchUserRank(agencyCode:String, scope: String, dateType: String): Flow<UserRankEntity>

    suspend fun fetchOurSchoolUserRank(scope: String, dateType: String): Flow<OurSchoolUserRankEntity>

    suspend fun searchUser(name: String, scope: String, agencyCode: String, grade:Int, classNum:Int): Flow<UserRankEntity>
}