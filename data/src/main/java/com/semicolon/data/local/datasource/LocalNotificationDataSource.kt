package com.semicolon.data.local.datasource

import com.semicolon.domain.entity.notification.NotificationEntity

interface LocalNotificationDataSource {

    suspend fun fetchNotificationList(): NotificationEntity

    suspend fun saveNotificationList(list: NotificationEntity)
}