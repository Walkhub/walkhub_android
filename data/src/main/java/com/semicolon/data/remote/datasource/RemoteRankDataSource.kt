package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.response.ranks.inquiryRank.school.SchoolRankResponse
import com.semicolon.data.remote.response.ranks.inquiryRank.user.UserRankResponse
import com.semicolon.data.remote.response.ranks.search.school.SearchSchoolResponse
import com.semicolon.data.remote.response.ranks.search.user.SearchUserResponse
import retrofit2.http.Query


interface RemoteRankDataSource {

    suspend fun fetchUserRank(scope: String, dataType: String, sort: String): UserRankResponse

    suspend fun fetchSchoolRank(dateType: String, sort: String): SchoolRankResponse

    suspend fun searchUser(name: String): SearchUserResponse

    suspend fun searchSchool(name: String): SearchSchoolResponse
}