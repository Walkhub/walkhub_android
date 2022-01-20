package com.semicolon.data.remote.response.notification

import com.google.gson.annotations.SerializedName

data class NotificationListResponse(
    @SerializedName("notification_list") val notification_list: NotificationList
){
    data class NotificationList(
        @SerializedName("id") val notificationId: Int,
        @SerializedName("title") val title: String,
        @SerializedName("content") val content: String,
        @SerializedName("type") val type: String,
        @SerializedName("value") val notificationCount: Int,
        @SerializedName("is_read") val isRead: Boolean
    )
}
