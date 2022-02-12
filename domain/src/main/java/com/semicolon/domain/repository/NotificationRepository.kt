package com.semicolon.domain.repository

import com.semicolon.domain.entity.notification.NotificationEntity
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun fetchNotificationList(): Flow<List<NotificationEntity>>

    suspend fun readNotification(notificationId: Int)
    
}