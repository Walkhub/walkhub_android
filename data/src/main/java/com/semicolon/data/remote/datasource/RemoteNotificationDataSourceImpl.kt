package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.NotificationApi
import com.semicolon.data.remote.response.notification.NotificationListResponse
import com.semicolon.data.util.HttpHandler
import javax.inject.Inject

class RemoteNotificationDataSourceImpl @Inject constructor(
    private val notificationApi: NotificationApi
) : RemoteNotificationDataSource {

    override suspend fun fetchNotificationList(): NotificationListResponse =
        HttpHandler<NotificationListResponse>()
            .httpRequest { notificationApi.loadNotifications() }
            .sendRequest()

    override suspend fun patchNotificationIsRead(data: Int) =
        HttpHandler<Unit>()
            .httpRequest { notificationApi.sendReadNotification(data) }
            .sendRequest()

    override suspend fun switchOnNotifications(userId: Int, type: String) =
        HttpHandler<Unit>()
            .httpRequest { notificationApi.onNotifications(userId, type) }
            .sendRequest()

    override suspend fun switchOffNotifications(type: String) =
        HttpHandler<Unit>()
            .httpRequest { notificationApi.offNotifications(type) }
            .sendRequest()

}
