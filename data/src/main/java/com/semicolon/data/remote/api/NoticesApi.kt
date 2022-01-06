package com.semicolon.data.remote.api

import com.semicolon.data.remote.request.notice.CreateNoticeRequest
import com.semicolon.data.remote.response.notice.noticedetail.NoticeDetailResponse
import com.semicolon.data.remote.response.notice.noticelist.NoticeListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NoticesApi {

    @GET("/notices/list")
    suspend fun loadNoticeList(

    ): NoticeListResponse

    @GET("/notices/{notice-id}")
    suspend fun loadNoticeDetail(
        @Path("notice-id") notice_id: Int
    ): NoticeDetailResponse
}