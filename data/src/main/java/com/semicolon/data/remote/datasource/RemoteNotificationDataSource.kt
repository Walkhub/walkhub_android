package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.request.notification.OffNotiRequest
import com.semicolon.data.remote.request.notification.OnNotiRequest
import com.semicolon.data.remote.response.notification.NotificationListResponse

interface RemoteNotificationDataSource {
    suspend fun fetchNotificationList(): NotificationListResponse

    suspend fun patchNotificationIsRead(data: Int)

    suspend fun switchOnNofications(onNotiRequest: OnNotiRequest)

    suspend fun switchOffNotifications(offNotiRequest: OffNotiRequest)
}