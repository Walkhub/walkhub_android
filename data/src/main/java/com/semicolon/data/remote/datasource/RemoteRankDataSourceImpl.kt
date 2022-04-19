package com.semicolon.data.remote.datasource


import com.semicolon.data.remote.api.RankApi
import com.semicolon.data.remote.response.ranks.FetchMySchoolRankResponse
import com.semicolon.data.remote.response.ranks.OurSchoolUserRankResponse
import com.semicolon.data.remote.response.ranks.inquiryRank.school.SchoolRankAndSearchResponse
import com.semicolon.data.remote.response.ranks.inquiryRank.user.UserRankResponse
import com.semicolon.data.remote.response.ranks.search.user.SearchUserResponse
import com.semicolon.data.util.HttpHandler
import javax.inject.Inject

class RemoteRankDataSourceImpl @Inject constructor(
    private val rankApi: RankApi
) : RemoteRankDataSource {

    override suspend fun fetchMySchoolRank(): FetchMySchoolRankResponse =
        HttpHandler<FetchMySchoolRankResponse>()
            .httpRequest { rankApi.fetchMySchoolRank() }
            .sendRequest()

    override suspend fun fetchSchoolRankAndSearch(
        name: String,
        schoolDateType: String
    ): SchoolRankAndSearchResponse =
        HttpHandler<SchoolRankAndSearchResponse>()
            .httpRequest { rankApi.fetchSchoolRankAndSearch(name, "RANK", "ALL", schoolDateType) }
            .sendRequest()

    override suspend fun fetchUserRank(
        schoolId: Int,
        dateType: String,
    ): UserRankResponse = HttpHandler<UserRankResponse>()
        .httpRequest { rankApi.fetchUserRank(schoolId, dateType) }
        .sendRequest()

    override suspend fun fetchOurSchoolUserRank(
        scope: String,
        moreDateType: String
    ): OurSchoolUserRankResponse =
        HttpHandler<OurSchoolUserRankResponse>()
            .httpRequest { rankApi.fetchOurSchoolUserRank(scope, moreDateType) }
            .sendRequest()

    override suspend fun searchUser(
        school: Int,
        name: String,
        moredateType: String
    ): SearchUserResponse =
        HttpHandler<SearchUserResponse>()
            .httpRequest { rankApi.searchUser(school, name, moredateType) }
            .sendRequest()
}



