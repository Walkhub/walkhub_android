package com.semicolon.data.remote.datasource


import com.semicolon.data.remote.api.RankApi
import com.semicolon.data.remote.response.ranks.inquiryRank.school.SchoolRankResponse
import com.semicolon.data.remote.response.ranks.inquiryRank.user.UserRankResponse
import com.semicolon.data.remote.response.ranks.search.school.SearchSchoolResponse
import com.semicolon.data.remote.response.ranks.search.user.SearchUserResponse
import com.semicolon.data.util.HttpHandler
import javax.inject.Inject

class RemoteRankDataSourceImpl @Inject constructor(
    private val rankApi: RankApi
) : RemoteRankDataSource {

    override suspend fun fetchUserRank(
        scope: String,
        dataType: String,
        sort: String
    ): UserRankResponse = HttpHandler<UserRankResponse>()
        .httpRequest { rankApi.fetchUserRank(scope, dataType, sort) }
        .sendRequest()


    override suspend fun fetchSchoolRank(dateType: String, sort: String): SchoolRankResponse =
        HttpHandler<SchoolRankResponse>()
            .httpRequest { rankApi.fetchSchoolRank(dateType, sort) }
            .sendRequest()


    override suspend fun searchUser(name: String): SearchUserResponse =
        HttpHandler<SearchUserResponse>()
            .httpRequest { rankApi.searchUser(name) }
            .sendRequest()

    override suspend fun searchSchool(name: String): SearchSchoolResponse =
        HttpHandler<SearchSchoolResponse>()
            .httpRequest { rankApi.searchSchool(name) }
            .sendRequest()
}



