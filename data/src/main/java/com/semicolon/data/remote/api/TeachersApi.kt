package com.semicolon.data.remote.api

import com.semicolon.data.remote.request.teacher.CreateClassRequest
import com.semicolon.data.remote.response.teacher.TeacherVerifyCodeResponse
import retrofit2.http.*

interface TeachersApi {

    @GET("/teachers/verify")
    fun getProduceTeacherVerifyCode(): TeacherVerifyCodeResponse

    @POST("/teachers/classes")
    fun createClass(createClassRequest: CreateClassRequest): TeacherVerifyCodeResponse

    @DELETE("/teachers/classes/{grade}/{class}/{agency-code}")
    fun deleteClass(
        @Query("grade") grade : Int,
        @Query("class") classes : Int,
        @Query("agency-code") agencyCode : String
    )
}