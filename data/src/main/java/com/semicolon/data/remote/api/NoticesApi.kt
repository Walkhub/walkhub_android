package com.semicolon.data.remote.api

import com.semicolon.data.remote.response.notice.NoticeDetailResponse
import com.semicolon.data.remote.response.notice.NoticeListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NoticesApi {
    @GET("/notices/list")
    suspend fun loadNoticeList(): NoticeListResponse

    @GET("/notices/{notice-id}")
    suspend fun loadNoticeDetail(
        @Path("notice-id") notice_id: Int
    ): NoticeDetailResponse
}