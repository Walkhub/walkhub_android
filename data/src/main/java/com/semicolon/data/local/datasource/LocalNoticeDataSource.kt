package com.semicolon.data.local.datasource

import com.semicolon.domain.entity.notice.NoticeEntity

interface LocalNoticeDataSource {
    suspend fun fetchNoticeList(): List<NoticeEntity>
}