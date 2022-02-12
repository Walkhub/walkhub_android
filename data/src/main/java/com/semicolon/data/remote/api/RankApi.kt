package com.semicolon.data.remote.api

import com.semicolon.data.remote.response.ranks.OurSchoolUserRankResponse
import com.semicolon.data.remote.response.ranks.inquiryRank.school.SchoolRankResponse
import com.semicolon.data.remote.response.ranks.inquiryRank.user.UserRankResponse
import com.semicolon.data.remote.response.ranks.search.school.SearchSchoolResponse
import com.semicolon.data.remote.response.ranks.search.user.SearchUserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RankApi {

    //학교랭킹조회
    @GET("/ranks/schools")
    suspend fun fetchSchoolRank(
        @Query("dateType") dateType: String
    ): SchoolRankResponse

    //학교검색
    @GET("/ranks/schools/search")
    suspend fun searchSchool(
        @Query("name") name: String,
        @Query("dateType") moreDateType: String
    ): SearchSchoolResponse

    //유저랭킹조회
    @GET("/ranks/users/{school-id}")
    suspend fun fetchUserRank(
        @Path("school-id") schoolId: Int,
        @Query("scope") scope: String,
        @Query("dateType") moreDateType: String
    ): UserRankResponse

    //우리학교 유저 랭킹
    @GET("/ranks/users/my-school")
    suspend fun fetchOurSchoolUserRank(
        @Query("scope") scope: String,
        @Query("dateType") moreDateType: String
    ): OurSchoolUserRankResponse

    //유저검색
    @GET("/ranks/users/search")
    suspend fun searchUser(
        @Query("name") name: String,
        @Query("scope") scope: String,
        @Query("dateType") moreDateType: String,
        @Query("grade") grade: Int,
        @Query("classNum") classNum:Int
    ): SearchUserResponse




}