package com.semicolon.data.remote.api

import com.semicolon.data.remote.response.ranks.OurSchoolUserRankResponse
import com.semicolon.data.remote.response.ranks.inquiryRank.school.SchoolRankResponse
import com.semicolon.data.remote.response.ranks.inquiryRank.user.UserRankResponse
import com.semicolon.data.remote.response.ranks.search.school.SearchSchoolResponse
import com.semicolon.data.remote.response.ranks.search.user.SearchUserResponse
import com.semicolon.domain.enum.RankScope
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
        @Query("name") name: String
    ): SearchSchoolResponse

    //유저랭킹조회
    @GET("/ranks/users/{agency-code}")
    suspend fun fetchUserRank(
        @Path("agency-code") agencyCode: String,
        @Query("scope") scope: String,
        @Query("dateType") dateType: String
    ): UserRankResponse

    //우리학교 유저 랭킹
    @GET("/ranks/users/my-school")
    suspend fun fetchOurSchoolUserRank(
        @Query("scope") scope: String,
        @Query("dateType") dateType: String
    ): OurSchoolUserRankResponse

    //유저검색
    @GET("/ranks/users/search")
    suspend fun searchUser(
        @Query("name") name: String,
        @Query("scope") scope: String,
        @Query("agencyCode") agencyCode: String,
        @Query("grade") grade: Int,
        @Query("classNum") classNum:Int
    ): SearchUserResponse




}