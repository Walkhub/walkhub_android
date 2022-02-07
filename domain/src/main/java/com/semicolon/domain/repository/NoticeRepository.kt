package com.semicolon.domain.repository

import com.semicolon.domain.entity.notice.NoticeEntity
import com.semicolon.domain.enum.NoticeType
import kotlinx.coroutines.flow.Flow

<<<<<<< HEAD
<<<<<<< HEAD
interface NoticeRepository{
    suspend fun loadNoticeList(): Flow<List<NoticeEntity>>
=======
interface NoticeRepository {
    suspend fun fetchNoticeList(noticeType: NoticeType): Flow<List<NoticeEntity>>
>>>>>>> develop
=======
interface NoticeRepository {
    suspend fun fetchNoticeList(noticeType: NoticeType): Flow<List<NoticeEntity>>
>>>>>>> 60_Notice_data
}