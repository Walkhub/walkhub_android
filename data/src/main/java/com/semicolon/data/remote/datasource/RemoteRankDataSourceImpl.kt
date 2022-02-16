package com.semicolon.data.remote.datasource


import com.semicolon.data.remote.api.RankApi
import com.semicolon.data.remote.response.ranks.OurSchoolUserRankResponse
import com.semicolon.data.remote.response.ranks.inquiryRank.school.SchoolRankResponse
import com.semicolon.data.remote.response.ranks.inquiryRank.user.UserRankResponse
import com.semicolon.data.remote.response.ranks.search.school.SearchSchoolResponse
import com.semicolon.data.remote.response.ranks.search.user.SearchUserResponse
import com.semicolon.data.util.HttpHandler
import javax.inject.Inject

class RemoteRankDataSourceImpl @Inject constructor(
    private val rankApi: RankApi
) : RemoteRankDataSource {

    override suspend fun fetchSchoolRank(dateType: String): SchoolRankResponse =
        HttpHandler<SchoolRankResponse>()
            .httpRequest { rankApi.fetchSchoolRank(dateType) }
            .sendRequest()

    override suspend fun searchSchool(name: String, moreDateType: String): SearchSchoolResponse =
        HttpHandler<SearchSchoolResponse>()
            .httpRequest { rankApi.searchSchool(name, moreDateType) }
            .sendRequest()

    override suspend fun fetchUserRank(
        schoolId: Int,
        scope: String,
        moreDateType: String,
    ): UserRankResponse = HttpHandler<UserRankResponse>()
        .httpRequest { rankApi.fetchUserRank(schoolId ,scope, moreDateType) }
        .sendRequest()

    override suspend fun fetchOurSchoolUserRank(
        scope: String,
        moreDateType: String
    ): OurSchoolUserRankResponse =
        HttpHandler<OurSchoolUserRankResponse>()
            .httpRequest { rankApi.fetchOurSchoolUserRank(scope, moreDateType) }
            .sendRequest()

    override suspend fun searchUser(
        name: String,
        scope: String,
        moreDateType: String,
        grade: Int,
        classNum: Int
    ): SearchUserResponse =
        HttpHandler<SearchUserResponse>()
            .httpRequest { rankApi.searchUser(name, scope, moreDateType, grade, classNum) }
            .sendRequest()

}



