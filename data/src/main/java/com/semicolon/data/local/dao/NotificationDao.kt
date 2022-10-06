package com.semicolon.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.semicolon.data.local.entity.notification.NotificationRoomEntity
import com.semicolon.data.local.entity.notification.NotificationStatusRoomEntity

@Dao
interface NotificationDao {

    @Query("SELECT * FROM notification")
    suspend fun fetchNotificationList(): NotificationRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNotification(notifications: NotificationRoomEntity)

    @Query("SELECT * FROM notificationStatus")
    suspend fun notificationStatus(): NotificationStatusRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNotificationStatus(notifications: NotificationStatusRoomEntity)
}
