package com.semicolon.walkhub.ui.notification.model

import com.semicolon.domain.enums.NotificationReturnType

data class NotificationData(
    val notificationValue: List<NotificationValue>
) {
    data class NotificationValue(
        val notificationId: Int,
        val title: String,
        val content: String,
        val type: String,
        val data: NotificationReturnType,
        val createAt: String,
        val isRead: Boolean,
        val writer: Writer
    ) {
        data class Writer(
            val writerId: Int,
            val writerName: String,
            val writerImage: String
        )
    }
}