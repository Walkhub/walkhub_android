package com.semicolon.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.semicolon.data.local.entity.notice.NoticeListRoomEntity

@Dao
interface NoticeDao {
    @Query("SELECT * FROM notice")
    suspend fun fetchNoticeList(): List<NoticeListRoomEntity>
}