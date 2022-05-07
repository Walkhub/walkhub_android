package com.semicolon.data.repository

import com.semicolon.data.local.datasource.LocalNotificationDataSource
import com.semicolon.data.remote.datasource.RemoteNotificationDataSource
import com.semicolon.data.remote.response.notification.toEntity
import com.semicolon.data.util.OfflineCacheUtil
import com.semicolon.domain.entity.notification.NotificationEntity
import com.semicolon.domain.enums.NotificationType
import com.semicolon.domain.param.notifications.SwitchOnNotificationsParam
import com.semicolon.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val localNotificationDataSource: LocalNotificationDataSource,
    private val remoteNotificationDataSource: RemoteNotificationDataSource
) : NotificationRepository {

    override suspend fun fetchNotificationList(): Flow<NotificationEntity> =
        OfflineCacheUtil<NotificationEntity>()
            .remoteData { remoteNotificationDataSource.fetchNotificationList().toEntity() }
            .localData { localNotificationDataSource.fetchNotificationList() }
            .doOnNeedRefresh { localNotificationDataSource.saveNotificationList(it) }
            .createFlow()

    override suspend fun readNotification(notificationId: Int) {
        remoteNotificationDataSource.patchNotificationIsRead(notificationId)
    }

    override suspend fun switchOnNotification(switchOnNotificationsParam: SwitchOnNotificationsParam) {
        remoteNotificationDataSource.switchOnNotifications(switchOnNotificationsParam.userId, switchOnNotificationsParam.type.toString())
    }

    override suspend fun switchOffNotification(type: NotificationType) {
        remoteNotificationDataSource.switchOffNotifications(type.toString())
    }
}
