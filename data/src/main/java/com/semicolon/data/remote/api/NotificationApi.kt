package com.semicolon.data.remote.api

import com.semicolon.data.remote.request.notification.OffNotiRequest
import com.semicolon.data.remote.request.notification.OnNotiRequest
import com.semicolon.data.remote.response.notification.NotificationListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface NotificationApi {
    @GET("notifications")
    suspend fun loadNotifications(): NotificationListResponse

    @PATCH("notifications/{notification-id}")
    suspend fun sendReadNotification(@Path("notification-id") notificationId: Int)

    @PATCH("notifications/on")
    suspend fun onNotifications(
        @Body onNotiRequest: OnNotiRequest
    )

    @PATCH("notifications")
    suspend fun offNotifications(
        @Body offNotiRequest: OffNotiRequest
    )
}