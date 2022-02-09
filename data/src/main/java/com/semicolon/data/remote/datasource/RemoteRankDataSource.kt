package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.response.ranks.OurSchoolUserRankResponse
import com.semicolon.data.remote.response.ranks.inquiryRank.school.SchoolRankResponse
import com.semicolon.data.remote.response.ranks.inquiryRank.user.UserRankResponse
import com.semicolon.data.remote.response.ranks.search.school.SearchSchoolResponse
import com.semicolon.data.remote.response.ranks.search.user.SearchUserResponse
import com.semicolon.domain.enum.MoreDateType


interface RemoteRankDataSource {
    suspend fun fetchSchoolRank(dateType: String): SchoolRankResponse

    suspend fun searchSchool(name: String, moreDateType: String): SearchSchoolResponse

    suspend fun fetchUserRank(schoolId: Int, scope: String, moreDateType: String): UserRankResponse

    suspend fun fetchOurSchoolUserRank(scope: String, moreDateType: String): OurSchoolUserRankResponse

    suspend fun searchUser(name: String, scope: String, moreDateType: String, grade: Int, classNum: Int): SearchUserResponse


}