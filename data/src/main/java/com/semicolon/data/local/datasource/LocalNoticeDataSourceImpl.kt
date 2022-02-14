package com.semicolon.data.local.datasource

import com.semicolon.data.local.dao.NoticeDao
import com.semicolon.data.local.entity.notice.NoticeListRoomEntity
import com.semicolon.data.local.entity.notice.toDbEntity
import com.semicolon.data.local.entity.notice.toEntity
import com.semicolon.domain.entity.notice.NoticeEntity
import javax.inject.Inject

class LocalNoticeDataSourceImpl @Inject constructor(
    private val noticeDao: NoticeDao
): LocalNoticeDataSource {

    override suspend fun fetchNoticeList(): NoticeListRoomEntity =
        noticeDao.fetchNoticeList().toEntity()

    override suspend fun saveNoticeList(notice: NoticeEntity): NoticeEntity =
        noticeDao.saveNoticeList(notice.toDbEntity())
}