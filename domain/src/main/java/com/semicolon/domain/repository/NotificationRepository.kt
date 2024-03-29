package com.semicolon.domain.repository

import com.semicolon.domain.entity.notification.NotificationEntity
import com.semicolon.domain.entity.notification.NotificationStatusEntity
import com.semicolon.domain.enums.NotificationType
import com.semicolon.domain.param.notifications.SwitchOffNotificationsParam
import com.semicolon.domain.param.notifications.SwitchOnNotificationsParam
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun fetchNotificationList(): Flow<NotificationEntity>

    suspend fun readNotification(notificationId: Int)

    suspend fun switchOnNotification(switchOnNotificationsParam: SwitchOnNotificationsParam)

    suspend fun switchOffNotification(switchOffNotificationsParam: SwitchOffNotificationsParam)

    suspend fun notificationStatus(): Flow<NotificationStatusEntity>
}