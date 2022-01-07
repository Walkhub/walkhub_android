package com.semicolon.data.remote.api

import com.semicolon.data.remote.response.ranks.inquiryRank.school.SchoolRankResponse
import com.semicolon.data.remote.response.ranks.inquiryRank.user.UserRankResponse
import com.semicolon.data.remote.response.ranks.search.school.SearchSchoolResponse
import com.semicolon.data.remote.response.ranks.search.user.SearchUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RankApi {
    //유저랭킹조회
    @GET("/ranks/users")
    suspend fun fetchUserRank(
        @Query("scope") scope: String,
        @Query("dateType") dateType: String,
        @Query("sort") sort: String
    ): UserRankResponse

    //학교랭킹조회
    @GET("/ranks/schools")
    suspend fun fetchSchoolRank(
        @Query("dateType") dateType: String,
        @Query("sort") sort: String
    ): SchoolRankResponse

    //유저검색
    @GET("/ranks/search/users")
    suspend fun searchUser(
        @Query("name") name: String
    ): SearchUserResponse

    //학교검색
    @GET("/ranks/search/schools")
    suspend fun searchSchool(
        @Query("name") name: String
    ): SearchSchoolResponse

}