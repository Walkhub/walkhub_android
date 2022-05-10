package com.semicolon.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.semicolon.data.local.entity.notification.NotificationRoomEntity

@Dao
interface NotificationDao {

    @Query("SELECT * FROM notification")
    suspend fun fetchNotificationList(): NotificationRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNotification(notifications: NotificationRoomEntity)
}
