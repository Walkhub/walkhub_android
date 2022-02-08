package com.semicolon.data.remote.response.notification

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.notification.NotificationListEntity

data class NotificationListResponse(
    @SerializedName("notification_list") val notificationList: List<NotificationList>
){
    data class NotificationList(
        @SerializedName("id") val notificationId: Int,
        @SerializedName("title") val title: String,
        @SerializedName("content") val content: String,
        @SerializedName("type") val type: String,
        @SerializedName("value") val notificationCount: Int,
        @SerializedName("is_read") val isRead: Boolean
    )

    fun NotificationList.toEntity(): NotificationListEntity =
        NotificationListEntity(
            notificationId = notificationId,
            title = title,
            content = content,
            type = type,
            notificationCount = notificationCount,
            isRead = isRead
        )
}

fun NotificationListResponse.toEntity(): List<NotificationListEntity> =
    notificationList.map { it.toEntity() }
