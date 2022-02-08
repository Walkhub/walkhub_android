package com.semicolon.domain.repository

import com.semicolon.domain.entity.notice.NoticeEntity
import com.semicolon.domain.entity.notification.NotificationListEntity
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun fetchNotificationList(): Flow<List<NotificationListEntity>>

    suspend fun patchNotificationIsRead(data: Int)
}