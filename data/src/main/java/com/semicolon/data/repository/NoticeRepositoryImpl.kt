package com.semicolon.data.repository

import com.semicolon.data.local.datasource.LocalNoticeDataSource
import com.semicolon.data.remote.datasource.RemoteNoticeDataSource
import com.semicolon.data.util.OfflineCacheUtil
import com.semicolon.domain.entity.notice.NoticeEntity
import com.semicolon.domain.enum.NoticeType
import com.semicolon.domain.repository.NoticeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoticeRepositoryImpl @Inject constructor(
    private val localNoticeDataSource: LocalNoticeDataSource,
    private val remoteNoticeDataSource: RemoteNoticeDataSource
) : NoticeRepository {

    override suspend fun fetchNoticeList(noticeType: NoticeType): Flow<List<NoticeEntity>> =
        OfflineCacheUtil<List<NoticeEntity>>()
            .remoteData { remoteNoticeDataSource.fetchNoticeList(noticeType.toString()) }
            .localData { localNoticeDataSource.fetchNoticeList() }
            .compareData { localData, remoteData -> localData.containsAll(remoteData) }
            .doOnNeedRefresh { localNoticeDataSource.saveNoticeList(it) }
            .createFlow()
}