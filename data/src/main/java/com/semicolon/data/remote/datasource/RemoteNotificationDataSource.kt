package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.response.notification.NotificationListResponse

interface RemoteNotificationDataSource {
    suspend fun fetchNotificationList(): NotificationListResponse

    suspend fun patchNotificationIsRead(data: Int)
}