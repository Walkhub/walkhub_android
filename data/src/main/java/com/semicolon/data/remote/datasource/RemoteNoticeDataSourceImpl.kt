package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.NoticesApi
import com.semicolon.data.remote.response.notice.NoticeListResponse
import com.semicolon.data.util.HttpHandler
import javax.inject.Inject

class RemoteNoticeDataSourceImpl @Inject constructor(
    private val noticesApi: NoticesApi
): RemoteNoticeDataSource {
    override suspend fun fetchNoticeList(
        noticeType: String
    ): NoticeListResponse =
        HttpHandler<NoticeListResponse>()
            .httpRequest { noticesApi.fetchNoticeList(noticeType) }
            .sendRequest()
}