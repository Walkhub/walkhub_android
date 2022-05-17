package com.semicolon.domain.entity.notification

import com.semicolon.domain.enums.NotificationType

data class NotificationStatusEntity(
    val topicWhetherList: List<TopicWhether>
) {
    data class TopicWhether(
        val isSubscribe: Boolean,
        val title: NotificationType,
        val topicId: Int
    )
}
