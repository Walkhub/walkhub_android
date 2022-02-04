package com.semicolon.domain.repository

import com.semicolon.domain.entity.notice.NoticeEntity
import com.semicolon.domain.enum.NoticeType
import kotlinx.coroutines.flow.Flow

interface NoticeRepository{
    suspend fun loadNoticeList(noticeType: NoticeType): Flow<List<NoticeEntity>>
}