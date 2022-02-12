package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.SchoolApi
import com.semicolon.data.remote.response.school.SearchSchoolResponse
import com.semicolon.data.util.HttpHandler
import javax.inject.Inject

class RemoteSchoolDataSourceImpl @Inject constructor(
    private val schoolApi: SchoolApi
): RemoteSchoolDataSource {

    override suspend fun searchSchool(name: String): SearchSchoolResponse =
        HttpHandler<SearchSchoolResponse>()
            .httpRequest { schoolApi.searchSchool(name) }
            .sendRequest()
}