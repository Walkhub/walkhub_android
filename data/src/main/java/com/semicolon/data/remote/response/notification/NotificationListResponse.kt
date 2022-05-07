package com.semicolon.data.remote.response.notification

import android.app.Notification
import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.notice.NoticeEntity
import com.semicolon.domain.entity.notification.NotificationEntity
import com.semicolon.domain.enums.NotificationReturnType
import java.time.LocalDateTime

data class NotificationListResponse(
    @SerializedName("notification_list") val notificationList: List<NotificationList>
) {
    data class NotificationList(
        @SerializedName("id") val notificationId: Int,
        @SerializedName("title") val title: String,
        @SerializedName("content") val content: String,
        @SerializedName("data") val data: NotificationReturnType,
        @SerializedName("type") val type: String,
        @SerializedName("create_at") val createAt: String,
        @SerializedName("is_read") val isRead: Boolean,
        @SerializedName("writer") val writer: Writer
    ) {
        data class Writer(
            @SerializedName("id") val writerId: Int,
            @SerializedName("name") val writerName: String,
            @SerializedName("profile_image_url") val writerImage: String
        )
    }

    fun NotificationList.toEntity(): NotificationEntity.NotificationValue =
        NotificationEntity.NotificationValue(
            notificationId = notificationId,
            title = title,
            content = content,
            data = data,
            type = type,
            createAt = createAt,
            isRead = isRead,
            writer = writer.toEntity()
        )

    fun NotificationList.Writer.toEntity(): NotificationEntity.NotificationValue.Writer =
        NotificationEntity.NotificationValue.Writer(
            writerId = writerId,
            writerName = writerName,
            writerImage = writerImage
        )
}

fun NotificationListResponse.toEntity() =
    NotificationEntity(
        notificationValue = notificationList.map { it.toEntity() }
    )

