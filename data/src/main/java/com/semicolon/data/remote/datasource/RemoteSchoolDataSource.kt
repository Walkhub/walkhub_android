package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.response.school.SearchSchoolResponse
import com.semicolon.domain.entity.school.SchoolDetailEntity
import com.semicolon.domain.entity.school.SearchSchoolEntity

interface RemoteSchoolDataSource {

    suspend fun searchSchool(name: String): SearchSchoolResponse

    suspend fun setSchoolLogo(imageUrl: String)

    suspend fun fetchSchoolDetail(schoolId: Int): SchoolDetailEntity
}