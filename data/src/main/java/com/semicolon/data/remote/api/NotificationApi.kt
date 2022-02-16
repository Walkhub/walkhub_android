package com.semicolon.data.remote.api

import com.semicolon.data.remote.response.notification.NotificationListResponse
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface NotificationApi {
    @GET("notifications")
    suspend fun loadNotifications(): NotificationListResponse

    @PATCH("notifications/{notification-id}")
    suspend fun sendReadNotification(@Path("notification-id") notificationId: Int)
}