package com.semicolon.data.remote.api

import com.semicolon.data.remote.response.notice.NoticeListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NoticesApi {
    @GET("notices/list")
    suspend fun fetchNoticeList(
        @Query("scope") scope: String,
        @Query("page") page: Int,
    ): NoticeListResponse
}