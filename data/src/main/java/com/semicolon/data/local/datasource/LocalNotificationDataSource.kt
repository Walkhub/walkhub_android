package com.semicolon.data.local.datasource

import com.semicolon.domain.entity.notification.NotificationListEntity

interface LocalNotificationDataSource {
    suspend fun fetchNotificationList(): List<NotificationListEntity>

    suspend fun saveNotificationList(list: List<NotificationListEntity>): List<NotificationListEntity>

}