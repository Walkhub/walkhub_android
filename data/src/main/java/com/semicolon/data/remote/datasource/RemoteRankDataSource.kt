package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.response.ranks.FetchMySchoolRankResponse
import com.semicolon.data.remote.response.ranks.OurSchoolUserRankResponse
import com.semicolon.data.remote.response.ranks.inquiryRank.school.SchoolRankAndSearchResponse
import com.semicolon.data.remote.response.ranks.inquiryRank.user.UserRankResponse
import com.semicolon.data.remote.response.ranks.search.user.SearchUserResponse

interface RemoteRankDataSource {
    suspend fun fetchMySchoolRank(): FetchMySchoolRankResponse

    suspend fun fetchSchoolRankAndSearch(
        name: String,
        schoolDateType: String
    ): SchoolRankAndSearchResponse

    suspend fun fetchUserRank(schoolId: Int, dateType: String): UserRankResponse

    suspend fun fetchOurSchoolUserRank(
        scope: String,
        moreDateType: String
    ): OurSchoolUserRankResponse

    suspend fun searchUser(school: Int, name: String, moredateType: String): SearchUserResponse
}