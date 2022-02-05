package com.semicolon.data.repository

import com.semicolon.data.remote.datasource.RemoteSchoolDataSource
import com.semicolon.data.remote.response.school.SearchSchoolResponse
import com.semicolon.data.remote.response.school.toListEntity
import com.semicolon.data.util.OfflineCacheUtil
import com.semicolon.domain.entity.school.SearchSchoolEntity
import com.semicolon.domain.repository.SchoolRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SchoolRepositoryImpl @Inject constructor(
    private val schoolDataSource: RemoteSchoolDataSource
) : SchoolRepository {
    override suspend fun searchSchool(name: String): Flow<List<SearchSchoolEntity>> =
        flow {
            schoolDataSource.searchSchool(name).toListEntity()
        }
}