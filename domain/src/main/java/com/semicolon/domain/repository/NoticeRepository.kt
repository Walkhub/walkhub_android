package com.semicolon.domain.repository

import com.semicolon.domain.entity.notice.NoticeEntity
import com.semicolon.domain.enums.NoticeType
import kotlinx.coroutines.flow.Flow

interface NoticeRepository{
    suspend fun fetchNoticeList(noticeType: NoticeType): Flow<NoticeEntity>
}