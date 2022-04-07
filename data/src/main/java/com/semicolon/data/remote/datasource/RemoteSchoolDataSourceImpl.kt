package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.SchoolApi
import com.semicolon.data.remote.response.school.SchoolDetailResponse
import com.semicolon.data.remote.response.school.SearchSchoolResponse
import com.semicolon.data.remote.response.school.toEntity
import com.semicolon.data.remote.response.school.toListEntity
import com.semicolon.data.util.HttpHandler
import com.semicolon.domain.entity.school.SchoolDetailEntity
import com.semicolon.domain.entity.school.SearchSchoolEntity
import javax.inject.Inject

class RemoteSchoolDataSourceImpl @Inject constructor(
    private val schoolApi: SchoolApi
): RemoteSchoolDataSource {

    override suspend fun searchSchool(name: String): List<SearchSchoolEntity> =
        HttpHandler<SearchSchoolResponse>()
            .httpRequest { schoolApi.searchSchool(name) }
            .sendRequest().toListEntity()

    override suspend fun setSchoolLogo(imageUrl: String) =
        HttpHandler<Unit>()
            .httpRequest { schoolApi.setSchoolLogo(imageUrl) }
            .sendRequest()

    override suspend fun fetchSchoolDetail(schoolId: Int): SchoolDetailEntity =
        HttpHandler<SchoolDetailResponse>()
            .httpRequest { schoolApi.fetchSchoolDetail(schoolId) }
            .sendRequest().toEntity()
}