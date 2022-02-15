package com.semicolon.data.remote.datasource

import com.semicolon.domain.entity.school.SearchSchoolEntity

interface RemoteSchoolDataSource {

    suspend fun searchSchool(name : String): List<SearchSchoolEntity>
}