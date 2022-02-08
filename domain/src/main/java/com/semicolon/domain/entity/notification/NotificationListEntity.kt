package com.semicolon.domain.entity.notification

data class NotificationListEntity(
    val notificationId: Int,
    val title: String,
    val content: String,
    val type: String,
    val notificationCount: Int,
    val isRead: Boolean
)