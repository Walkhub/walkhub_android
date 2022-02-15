package com.semicolon.data.local.datasource

import com.semicolon.data.local.entity.notice.NoticeListRoomEntity
import com.semicolon.domain.entity.notice.NoticeEntity

interface LocalNoticeDataSource {
    suspend fun fetchNoticeList(): NoticeListRoomEntity

    suspend fun saveNoticeList(list: NoticeEntity): NoticeEntity
}