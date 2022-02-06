package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.response.challenge.ChallengeListResponse
import com.semicolon.data.remote.response.notice.NoticeListResponse

interface RemoteNoticeDataSource {
    suspend fun fetchNoticeList(): NoticeListResponse
}