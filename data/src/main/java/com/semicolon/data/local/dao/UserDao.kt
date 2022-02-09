package com.semicolon.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.semicolon.data.local.entity.user.UserMyPageRoomEntity
import com.semicolon.data.local.entity.user.UserProfileRoomEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM userMyPage")
    suspend fun fetchUserMyPage() : UserMyPageRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserMyPage(userMyPageRoomEntity: UserMyPageRoomEntity)

    @Query("SELECT * FROM userProfile WHERE userId = :userId")
    suspend fun fetchUserProfile(userId: Int) : UserProfileRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(userUserProfileRoomEntity: UserProfileRoomEntity)

}