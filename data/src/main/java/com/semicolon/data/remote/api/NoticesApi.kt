package com.semicolon.data.remote.api

import com.semicolon.data.remote.response.notice.NoticeListResponse
import retrofit2.http.GET

interface NoticesApi {
    @GET("/notices/list")
    suspend fun fetchNoticeList(): NoticeListResponse
}