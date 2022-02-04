package com.semicolon.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.semicolon.data.local.entity.user.UserMyPageRoomEntity
import com.semicolon.data.local.entity.user.UserOwnBadgeRoomEntity
import com.semicolon.data.local.entity.user.UserProfileRoomEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM userMyPage")
    suspend fun fetchUserMyPage() : UserMyPageRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserMyPage(userMyPageRoomEntity: UserMyPageRoomEntity)

    @Query("SELECT * FROM userProfile WHERE id = :userId")
    suspend fun fetchUserProfile(userId: Int) : UserProfileRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(userUserProfileRoomEntity: UserProfileRoomEntity)

    @Query("SELECT * FROM ownBadge WHERE id = :userId")
    suspend fun fetchUserOwnBadge(userId: Int) : UserOwnBadgeRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserOwnBadge(userOwnBadgeRoomEntity: UserOwnBadgeRoomEntity)

}