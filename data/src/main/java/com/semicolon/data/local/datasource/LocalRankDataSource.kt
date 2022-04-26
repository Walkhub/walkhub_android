package com.semicolon.data.local.datasource


import com.semicolon.domain.entity.rank.*

interface LocalRankDataSource {

    suspend fun fetchMySchoolRank(): FetchMySchoolRankEntity

    suspend fun insertFetchMySchoolRank(fetchMySchoolRankEntity: FetchMySchoolRankEntity)

    suspend fun fetchSchoolAndSearchRank(): SchoolRankAndSearchEntity

    suspend fun insertSchoolAndSearchRank(schoolRankAndSearch: SchoolRankAndSearchEntity)

    suspend fun fetchUserRank(): UserRankEntity

    suspend fun insertUserRank(userRank: UserRankEntity)

    suspend fun fetchOurSchoolUserRank(): OurSchoolUserRankEntity

    suspend fun insertOurSchoolUserRank(ourSchoolUserRank: OurSchoolUserRankEntity)

    suspend fun searchUser(): SearchUserEntity

    suspend fun insertSearchUser(searchUser: SearchUserEntity)
}