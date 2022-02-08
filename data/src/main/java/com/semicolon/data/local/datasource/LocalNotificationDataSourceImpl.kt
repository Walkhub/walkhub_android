package com.semicolon.data.local.datasource

import com.semicolon.data.local.dao.NotificationDao
import com.semicolon.data.local.entity.notification.toDbEntity
import com.semicolon.data.local.entity.notification.toEntity
import com.semicolon.domain.entity.notification.NotificationListEntity
import javax.inject.Inject

class LocalNotificationDataSourceImpl @Inject constructor(
    private val notificationDao: NotificationDao
): LocalNotificationDataSource {

    override suspend fun fetchNotificationList(): List<NotificationListEntity> =
        notificationDao.fetchNotificationList().toEntity()

    override suspend fun saveNotificationList(notifications: List<NotificationListEntity>): List<NotificationListEntity> =
         notificationDao.saveNotification(notifications.toDbEntity())

}