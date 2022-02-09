package com.semicolon.domain.entity.notification

data class NotificationEntity(
    val notificationId: Int,
    val title: String,
    val content: String,
    val type: String,
    val notificationCount: Int,
    val isRead: Boolean
)