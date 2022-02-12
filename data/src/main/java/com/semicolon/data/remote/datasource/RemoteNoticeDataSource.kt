package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.response.challenge.ChallengeListResponse
import com.semicolon.data.remote.response.notice.NoticeListResponse
import com.semicolon.domain.enum.NoticeType

interface RemoteNoticeDataSource {
    suspend fun fetchNoticeList(scope: String): NoticeListResponse
}