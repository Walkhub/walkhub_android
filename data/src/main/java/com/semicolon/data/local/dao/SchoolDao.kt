package com.semicolon.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.semicolon.data.local.entity.school.SchoolDetailRoomEntity

@Dao
interface SchoolDao {

    @Query("SELECT * FROM schoolDetail")
    suspend fun fetchSchoolDetail() : SchoolDetailRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchoolDetail(schoolDetailRoomEntity: SchoolDetailRoomEntity)
}