package com.semicolon.data.local.datasource


import com.semicolon.domain.entity.rank.*

interface LocalRankDataSource {

    suspend fun fetchSchoolRank(): SchoolRankEntity

    suspend fun insertSchoolRank(schoolRank: SchoolRankEntity)

    suspend fun searchSchool(): SearchSchoolEntity

    suspend fun insertSearchSchool(searchSchool: SearchSchoolEntity)

    suspend fun fetchUserRank(): UserRankEntity

    suspend fun insertUserRank(userRank: UserRankEntity)

    suspend fun fetchOurSchoolUserRank(): OurSchoolUserRankEntity

    suspend fun insertOurSchoolUserRank(ourSchoolUserRank: OurSchoolUserRankEntity)

    suspend fun searchUser(): SearchUserEntity

    suspend fun insertSearchUser(searchUser: SearchUserEntity)
}