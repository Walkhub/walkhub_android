package com.semicolon.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.semicolon.data.local.entity.notice.NoticeListRoomEntity
import com.semicolon.domain.entity.notice.NoticeEntity

@Dao
interface NoticeDao {
    @Query("SELECT * FROM notice")
    suspend fun fetchNoticeList(): List<NoticeListRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNoticeList(notice: List<NoticeListRoomEntity>): List<NoticeEntity>
}