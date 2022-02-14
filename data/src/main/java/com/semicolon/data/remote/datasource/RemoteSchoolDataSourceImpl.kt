package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.SchoolApi
import com.semicolon.data.remote.response.school.SearchSchoolResponse
import com.semicolon.data.remote.response.school.toListEntity
import com.semicolon.data.util.HttpHandler
import com.semicolon.domain.entity.school.SearchSchoolEntity
import javax.inject.Inject

class RemoteSchoolDataSourceImpl @Inject constructor(
    private val schoolApi: SchoolApi
): RemoteSchoolDataSource {

    override suspend fun searchSchool(name: String): List<SearchSchoolEntity> =
        HttpHandler<SearchSchoolResponse>()
            .httpRequest { schoolApi.searchSchool(name) }
            .sendRequest().toListEntity()
}