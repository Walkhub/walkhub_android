package com.semicolon.domain.entity.notification

data class NotificationStatusEntity(
    val topicWhetherList: List<TopicWhether>
) {
    data class TopicWhether(
        val isSubscribe: Boolean,
        val title: String,
        val topicId: Int
    )
}
