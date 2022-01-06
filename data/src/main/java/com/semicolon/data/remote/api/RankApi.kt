package com.semicolon.data.remote.api

import com.semicolon.data.remote.response.CheckSchoolRank
import com.semicolon.data.remote.response.CheckUserRank
import com.semicolon.data.remote.response.SearchSchool
import com.semicolon.data.remote.response.SearchUser
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RankApi {
    //유저랭킹조회
    @GET("/ranks/users")
    suspend fun fetchCheckUserRanks(
        @Query("scope") scope: String,
        @Query("dateType") dateType: String,
        @Query("sort") sort: String
    ): CheckUserRank

    //학교랭킹조회
    @GET("/ranks/schools")
    suspend fun fetchCheckSchoolRanks(
        @Query("dateType") dateType: String,
        @Query("sort") sort: String
    ): CheckSchoolRank

    //유저검색
    @GET("/ranks/search/users")
    suspend fun fetchSearchUser(
        @Query("name") name: String
    ): SearchUser

    //학교검색
    @GET("/ranks/search/schools")
    suspend fun fetchSearchSchool(
        @Query("name") name: String
    ): SearchSchool

}