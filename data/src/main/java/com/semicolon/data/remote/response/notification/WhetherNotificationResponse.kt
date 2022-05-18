package com.semicolon.data.remote.response.notification

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.notification.NotificationStatusEntity
import com.semicolon.domain.enums.NotificationType

data class WhetherNotificationResponse(
    @SerializedName("topic_whether_list") val topicWhetherList: List<TopicWhether>
) {
    data class TopicWhether(
        @SerializedName("is_subscribe") val isSubscribe: Boolean,
        @SerializedName("title") val title: NotificationType,
        @SerializedName("topic_id") val topicId: Int
    )
}

fun WhetherNotificationResponse.TopicWhether.toEntity(): NotificationStatusEntity.TopicWhether =
    com.semicolon.domain.entity.notification.NotificationStatusEntity.TopicWhether(
        isSubscribe = isSubscribe,
        title = title,
        topicId = topicId
    )

fun WhetherNotificationResponse.toEntity() =
    NotificationStatusEntity(
        topicWhetherList = topicWhetherList.map { it.toEntity() }
    )