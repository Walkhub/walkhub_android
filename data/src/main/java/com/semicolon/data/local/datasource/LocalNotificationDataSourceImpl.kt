package com.semicolon.data.local.datasource

import com.semicolon.data.local.dao.NotificationDao
import com.semicolon.data.local.entity.notification.toDbEntity
import com.semicolon.data.local.entity.notification.toEntity
import com.semicolon.domain.entity.notification.NotificationEntity
import com.semicolon.domain.entity.notification.NotificationStatusEntity
import javax.inject.Inject

class LocalNotificationDataSourceImpl @Inject constructor(
    private val notificationDao: NotificationDao
): LocalNotificationDataSource {

    override suspend fun fetchNotificationList(): NotificationEntity =
        notificationDao.fetchNotificationList().toEntity()

    override suspend fun saveNotificationList(list: NotificationEntity) =
         notificationDao.saveNotification(list.toDbEntity())

    override suspend fun fetchNotificationStatus(): NotificationStatusEntity =
        notificationDao.notificationStatus().toEntity()

    override suspend fun saveNotificationStatus(list: NotificationStatusEntity) =
        notificationDao.saveNotificationStatus(list.toDbEntity())

}