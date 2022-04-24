package com.semicolon.data.remote.api

import com.semicolon.data.remote.response.school.SchoolDetailResponse
import com.semicolon.data.remote.response.school.SearchSchoolResponse
import retrofit2.http.*

interface SchoolApi {

    @GET("schools/search")
    suspend fun searchSchool(
        @Query("name") name: String
    ): SearchSchoolResponse

    @PATCH("schools/logos")
    suspend fun setSchoolLogo(
        @Body image_url: String
    )

    @GET("schools/details/{school-id}")
    suspend fun fetchSchoolDetail(
        @Path("school-id") schoolId: Int
    ): SchoolDetailResponse
}