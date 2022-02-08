package com.semicolon.data.local.entity.notification

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.notification.NotificationListEntity

@Entity(tableName = "notification")
data class NotificationRoomEntity(
    @PrimaryKey val notificationId: Int,
    val title: String,
    val content: String,
    val type: String,
    val notificationCount: Int,
    val isRead: Boolean
)

fun NotificationRoomEntity.toEntity() =
    NotificationListEntity(
        notificationId = notificationId,
        title = title,
        content = content,
        type = type,
        notificationCount = notificationCount,
        isRead = isRead
    )

fun List<NotificationRoomEntity>.toEntity() =
    map { it.toEntity() }

fun NotificationListEntity.toDbEntity() =
    NotificationRoomEntity(
        notificationId = notificationId,
        title = title,
        content = content,
        type = type,
        notificationCount = notificationCount,
        isRead = isRead
    )

fun List<NotificationListEntity>.toDbEntity() =
    map { it.toDbEntity() }


