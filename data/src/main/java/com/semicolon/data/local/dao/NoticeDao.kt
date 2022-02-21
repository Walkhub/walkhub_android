package com.semicolon.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.semicolon.data.local.entity.notice.NoticeListRoomEntity
import com.semicolon.domain.enum.NoticeType

@Dao
interface NoticeDao {

    @Query("SELECT * FROM noticeList")
    suspend fun fetchNoticeList(): NoticeListRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNoticeList(noticeListRoomEntity: NoticeListRoomEntity)
}