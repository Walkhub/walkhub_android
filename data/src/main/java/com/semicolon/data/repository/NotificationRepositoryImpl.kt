package com.semicolon.data.repository

import com.semicolon.data.local.datasource.LocalNotificationDataSource
import com.semicolon.data.remote.datasource.RemoteNotificationDataSource
import com.semicolon.data.remote.request.notification.OffNotiRequest
import com.semicolon.data.remote.request.notification.OnNotiRequest
import com.semicolon.data.remote.response.notification.toEntity
import com.semicolon.data.util.OfflineCacheUtil
import com.semicolon.domain.entity.notification.NotificationEntity
import com.semicolon.domain.entity.notification.NotificationStatusEntity
import com.semicolon.domain.param.notifications.SwitchOffNotificationsParam
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
        remoteNotificationDataSource.switchOnNofications(switchOnNotificationsParam.toRequest())
    }

    override suspend fun switchOffNotification(switchOffNotificationsParam: SwitchOffNotificationsParam) =
        remoteNotificationDataSource.switchOffNotifications(switchOffNotificationsParam.toRequest())

    override suspend fun notificationStatus(): Flow<NotificationStatusEntity> =
        OfflineCacheUtil<NotificationStatusEntity>()
            .remoteData { remoteNotificationDataSource.notificationStatus().toEntity() }
            .localData { localNotificationDataSource.fetchNotificationStatus() }
            .doOnNeedRefresh { localNotificationDataSource.saveNotificationStatus(it) }
            .createFlow()

    fun SwitchOffNotificationsParam.toRequest() =
        OffNotiRequest(
            type = type,
            userIdList = listOf(userId)
        )

    fun SwitchOnNotificationsParam.toRequest() =
        OnNotiRequest(
            type = type,
            userIdList = listOf(userId)

        )

}


