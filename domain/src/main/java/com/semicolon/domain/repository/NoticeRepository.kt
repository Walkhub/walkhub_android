package com.semicolon.domain.repository

import com.semicolon.domain.entity.notice.NoticeEntity
import kotlinx.coroutines.flow.Flow

interface NoticeRepository{
    suspend fun loadNoticeList(): Flow<List<NoticeEntity>>
}