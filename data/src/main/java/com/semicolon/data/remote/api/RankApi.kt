package com.semicolon.data.remote.api

import com.semicolon.data.remote.response.ranks.FetchMySchoolRankResponse
import com.semicolon.data.remote.response.ranks.OurSchoolUserRankResponse
import com.semicolon.data.remote.response.ranks.inquiryRank.school.SchoolRankAndSearchResponse
import com.semicolon.data.remote.response.ranks.inquiryRank.user.UserRankResponse
import com.semicolon.data.remote.response.ranks.search.user.SearchUserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RankApi {
    // 내 학교 랭킹 조회
    @GET("ranks/schools")
    suspend fun fetchMySchoolRank(): FetchMySchoolRankResponse

    // 학교 랭킹 조회 및 검색
    @GET("ranks/schools/search")
    suspend fun fetchSchoolRankAndSearch(
        @Query("name") name: String,
        @Query("sort") sort: String,
        @Query("scope") scope: String,
        @Query("schoolDateType") schoolDateType: String
    ): SchoolRankAndSearchResponse

    // 다른 학교 유저 랭킹 조회
    @GET("ranks/users/{school-id}")
    suspend fun fetchUserRank(
        @Path("school-id") schoolId: Int,
        @Query("dateType") dateType: String
    ): UserRankResponse

    // 우리 학교 유저 랭킹 조회
    @GET("ranks/users/my-school")
    suspend fun fetchOurSchoolUserRank(
        @Query("scope") scope: String,
        @Query("dateType") moreDateType: String
    ): OurSchoolUserRankResponse

    // 유저 검색
    @GET("ranks/users/search/{school-id}")
    suspend fun searchUser(
        @Path("school-id") school: Int,
        @Query("name") name: String,
        @Query("dateType") moreDateType: String
    ): SearchUserResponse


}