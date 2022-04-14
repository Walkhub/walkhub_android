package com.semicolon.data.remote.datasource

import com.semicolon.domain.entity.school.SchoolDetailEntity
import com.semicolon.domain.entity.school.SearchSchoolEntity

interface RemoteSchoolDataSource {

    suspend fun searchSchool(name : String): List<SearchSchoolEntity>

    suspend fun setSchoolLogo(imageUrl: String)

    suspend fun fetchSchoolDetail(schoolId: Int): SchoolDetailEntity
}