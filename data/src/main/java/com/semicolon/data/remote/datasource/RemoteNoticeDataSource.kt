package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.response.notice.NoticeListResponse

interface RemoteNoticeDataSource {
    suspend fun fetchNoticeList(noticeType: String): NoticeListResponse
}