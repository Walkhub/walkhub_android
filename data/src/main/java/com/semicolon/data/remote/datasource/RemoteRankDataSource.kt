package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.response.ranks.OurSchoolUserRankResponse
import com.semicolon.data.remote.response.ranks.inquiryRank.school.SchoolRankResponse
import com.semicolon.data.remote.response.ranks.inquiryRank.user.UserRankResponse
import com.semicolon.data.remote.response.ranks.search.school.SearchSchoolResponse
import com.semicolon.data.remote.response.ranks.search.user.SearchUserResponse


interface RemoteRankDataSource {
    suspend fun fetchSchoolRank(dateType: String): SchoolRankResponse

    suspend fun searchSchool(name: String): SearchSchoolResponse

    suspend fun fetchUserRank(agencyCode: String, scope: String, dateType: String): UserRankResponse

    suspend fun fetchOurSchoolUserRank(scope: String, dateType: String): OurSchoolUserRankResponse

    suspend fun searchUser(name: String, scope: String, agencyCode: String, grade: Int, classNum: Int): SearchUserResponse


}