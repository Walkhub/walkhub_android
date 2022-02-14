package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.NoticesApi
import com.semicolon.data.remote.response.image.ImagesResponse
import com.semicolon.data.remote.response.notice.NoticeListResponse
import com.semicolon.data.util.HttpHandler
import com.semicolon.domain.enum.NoticeType
import okhttp3.MultipartBody
import javax.inject.Inject

class RemoteNoticeDataSourceImpl @Inject constructor(
    private val noticesApi: NoticesApi
): RemoteNoticeDataSource {
    override suspend fun fetchNoticeList(
        scope: String
    ): NoticeListResponse =
        HttpHandler<NoticeListResponse>()
            .httpRequest { noticesApi.fetchNoticeList(scope) }
            .sendRequest()
}