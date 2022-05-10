package com.semicolon.domain.repository

import com.semicolon.domain.entity.notice.NoticeEntity
import com.semicolon.domain.enums.NoticeType
import com.semicolon.domain.param.notice.FetchNoticeListParam
import kotlinx.coroutines.flow.Flow

interface NoticeRepository{
    suspend fun fetchNoticeList(fetchNoticeListParam: FetchNoticeListParam): Flow<NoticeEntity>
}