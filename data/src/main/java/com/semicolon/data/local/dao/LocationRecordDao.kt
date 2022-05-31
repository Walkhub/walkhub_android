package com.semicolon.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.semicolon.data.local.entity.exercise.LocationRecordRoomEntity

@Dao
interface LocationRecordDao {

    @Query("SELECT * FROM LocationRecordRoomEntity")
    fun fetchLocationRecords(): List<LocationRecordRoomEntity>

    @Insert
    fun addLocationRecords(locationRecords: List<LocationRecordRoomEntity>)

    @Query("DELETE FROM LocationRecordRoomEntity")
    fun clearLocationRecords()
}