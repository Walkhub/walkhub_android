package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.response.school.SearchSchoolResponse

interface RemoteSchoolDataSource {

    suspend fun searchSchool(name : String): SearchSchoolResponse
}