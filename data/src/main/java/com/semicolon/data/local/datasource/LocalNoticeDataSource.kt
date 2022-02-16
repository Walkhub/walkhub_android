package com.semicolon.data.local.datasource

import com.semicolon.domain.entity.notice.NoticeEntity
import com.semicolon.domain.enum.NoticeType

interface LocalNoticeDataSource {

    suspend fun fetchNoticeList(): NoticeEntity

    suspend fun saveNoticeList(list: NoticeEntity)
}