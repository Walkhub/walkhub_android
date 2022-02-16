package com.semicolon.data.local.entity.notification

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.notice.NoticeEntity
import com.semicolon.domain.entity.notification.NotificationEntity

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
    NotificationEntity(
        notificationId = notificationId,
        title = title,
        content = content,
        type = type,
        notificationCount = notificationCount,
        isRead = isRead
    )

fun List<NotificationRoomEntity>.toEntity() =
    map { it.toEntity() }

fun NotificationEntity.toDbEntity() =
    NotificationRoomEntity(
        notificationId = notificationId,
        title = title,
        content = content,
        type = type,
        notificationCount = notificationCount,
        isRead = isRead
    )

fun List<NotificationEntity>.toDbEntity() =
    map { it.toDbEntity() }


