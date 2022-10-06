package com.semicolon.data.local.datasource

import com.semicolon.domain.entity.notification.NotificationEntity
import com.semicolon.domain.entity.notification.NotificationStatusEntity

interface LocalNotificationDataSource {

    suspend fun fetchNotificationList(): NotificationEntity

    suspend fun saveNotificationList(list: NotificationEntity)

    suspend fun fetchNotificationStatus(): NotificationStatusEntity

    suspend fun saveNotificationStatus(list: NotificationStatusEntity)
}