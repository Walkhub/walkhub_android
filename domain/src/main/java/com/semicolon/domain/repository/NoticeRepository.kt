package com.semicolon.domain.repository

import com.semicolon.domain.entity.notice.NoticeDetailEntity
import com.semicolon.domain.entity.notice.NoticeEntity
import kotlinx.coroutines.flow.Flow

interface NoticeRepository{
    suspend fun loadNoticeList(data: Int): Flow<List<NoticeEntity>>

    suspend fun loadNoticeDetail(data: Int): Flow<NoticeDetailEntity>
}