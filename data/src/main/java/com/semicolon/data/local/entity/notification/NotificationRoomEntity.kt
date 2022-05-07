package com.semicolon.data.local.entity.notification

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.notification.NotificationEntity
import com.semicolon.domain.enums.NotificationReturnType

@Entity(tableName = "notification")
data class NotificationRoomEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val notificationRoomValue: List<NotificationRoomValue>
) {
    data class NotificationRoomValue(
        val notificationId: Int,
        val title: String,
        val content: String,
        val type: String,
        val data: NotificationReturnType,
        val createAt: String,
        val isRead: Boolean,
        @Embedded val writer: Writer
    ) {
        data class Writer(
            val writerId: Int,
            val writerName: String,
            val writerImage: String
        )
    }

    fun NotificationRoomValue.toEntity() =
        NotificationEntity.NotificationValue(
            notificationId = notificationId,
            title = title,
            content = content,
            type = type,
            data = data,
            createAt = createAt,
            isRead = isRead,
            writer = writer.toEntity()
        )

    fun NotificationRoomValue.Writer.toEntity() =
        NotificationEntity.NotificationValue.Writer(
            writerId = writerId,
            writerName = writerName,
            writerImage = writerImage
        )
}

fun NotificationRoomEntity.toEntity() =
    notificationRoomValue.map { it.toEntity() }?.let {
        NotificationEntity(
            notificationValue = it
        )
    }

fun NotificationEntity.toDbEntity() =
    NotificationRoomEntity(
        notificationRoomValue = notificationValue?.map { it.toDbEntity() }
    )

fun NotificationEntity.NotificationValue.toDbEntity() =
    NotificationRoomEntity.NotificationRoomValue(
        notificationId = notificationId,
        title = title,
        content = content,
        type = type,
        data = data,
        createAt = createAt,
        isRead = isRead,
        writer = writer.toDbEntity()
    )

fun NotificationEntity.NotificationValue.Writer.toDbEntity() =
    NotificationRoomEntity.NotificationRoomValue.Writer(
        writerId = writerId,
        writerName = writerName,
        writerImage = writerImage
    )





