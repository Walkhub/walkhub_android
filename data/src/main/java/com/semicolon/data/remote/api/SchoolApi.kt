package com.semicolon.data.remote.api

import com.semicolon.data.remote.response.school.SchoolDetailResponse
import com.semicolon.data.remote.response.school.SearchSchoolResponse
import retrofit2.http.*

interface SchoolApi {

    @GET("schools/search")
    fun searchSchool(
        @Query("name") name: String
    ): SearchSchoolResponse

    @PATCH("schools/logos")
    fun setSchoolLogo(
        @Body image_url: String
    )

    @GET("schools/details/{school-id}")
    fun fetchSchoolDetail(
        @Path("school-id") schoolId: Int
    ): SchoolDetailResponse
}