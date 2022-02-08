package com.semicolon.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.semicolon.data.local.entity.notification.NotificationRoomEntity
import com.semicolon.domain.entity.notification.NotificationListEntity

interface NotificationDao {
    @Query("SELECT * FROM notification")
    suspend fun fetchNotificationList(): List<NotificationRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNotification(notifications: List<NotificationRoomEntity>): List<NotificationListEntity>
}
