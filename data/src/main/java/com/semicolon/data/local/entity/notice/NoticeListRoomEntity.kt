package com.semicolon.data.local.entity.notice

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.notice.NoticeEntity
import java.time.LocalDateTime

@Entity(tableName = "noticeList")
data class NoticeListRoomEntity(
    @PrimaryKey (autoGenerate = true) var id: Int = 0,
    val noticeList: List<NoticeListValue>
) {
    data class NoticeListValue(
        val id: Int,
        val content: String,
        val createdAt: LocalDateTime,
        @Embedded val noticeWriter: NoticeWriter
    ) {
        data class NoticeWriter(
            val id: Int,
            val name: String,
            val profileImageUrl: String
        )
    }

    fun NoticeListValue.toEntity() =
        NoticeEntity.NoticeValueEntity(
            id = id,
            content = content,
            createdAt = createdAt,
            noticeWriter = noticeWriter.toEntity()
        )

    fun NoticeListValue.NoticeWriter.toEntity() =
        NoticeEntity.NoticeValueEntity.NoticeWriterEntity(
            id = id,
            name = name,
            profileUrl = profileImageUrl
        )
}

fun NoticeListRoomEntity.toEntity() =
    NoticeEntity(
        noticeValueEntity = noticeList.map { it.toEntity() }
    )

fun NoticeEntity.toDbEntity() =
    NoticeListRoomEntity(
        noticeList = noticeValueEntity.map { it.toDbEntity() }
    )

fun NoticeEntity.NoticeValueEntity.toDbEntity() =
    NoticeListRoomEntity.NoticeListValue(
        id = id,
        content = content,
        createdAt = createdAt,
        noticeWriter = noticeWriter.toDbEntity()
    )

fun NoticeEntity.NoticeValueEntity.NoticeWriterEntity.toDbEntity() =
    NoticeListRoomEntity.NoticeListValue.NoticeWriter(
        id = id,
        name = name,
        profileImageUrl = profileUrl
    )
