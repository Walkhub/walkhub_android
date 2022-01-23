package com.semicolon.data.remote.api

import com.semicolon.data.remote.response.school.SearchSchoolResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolApi {

    @GET("schools/search/schools")
    fun searchSchool(
        @Query("name") name: String
    ): SearchSchoolResponse
}