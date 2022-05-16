package com.semicolon.data.local.entity.notification

import androidx.lifecycle.Transformations.map
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.notification.NotificationEntity
import com.semicolon.domain.entity.notification.NotificationStatusEntity

@Entity(tableName = "notificationStatus")
data class NotificationStatusRoomEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val notificationStatusRoom: List<TopicWhether>
)  {
    data class TopicWhether(
        val isSubscribe: Boolean,
        val title: String,
        val topicId: Int
    )

    fun TopicWhether.toEntity() =
        NotificationStatusEntity.TopicWhether(
            isSubscribe = isSubscribe,
            title = title,
            topicId = topicId
        )
}

fun NotificationStatusRoomEntity.toEntity() =
    notificationStatusRoom.map { it.toEntity() }?.let {
        NotificationStatusEntity(
            topicWhetherList = it
        )
    }

fun NotificationStatusEntity.toDbEntity() =
    NotificationStatusRoomEntity(
        notificationStatusRoom = topicWhetherList?.map { it.toDbEntity() }
    )

fun NotificationStatusEntity.TopicWhether.toDbEntity() =
    NotificationStatusRoomEntity.TopicWhether(
        isSubscribe = isSubscribe,
        title = title,
        topicId = topicId
    )
