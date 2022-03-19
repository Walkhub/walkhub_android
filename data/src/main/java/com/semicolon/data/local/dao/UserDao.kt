package com.semicolon.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.semicolon.data.local.entity.user.*

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

    @Query("SELECT * FROM caloriesLevel")
    suspend fun fetchCaloriesLevelList(): FetchCaloriesLevelRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCaloriesLevelList(insertCaloriesLevelRoomEntity: FetchCaloriesLevelRoomEntity)

    @Query("SELECT * FROM dailyGoal")
    suspend fun fetchDailyGoal(): FetchDailyGoalRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyGoal(fetchDailyGoalRoomEntity: FetchDailyGoalRoomEntity)

    @Query("SELECT * FROM userInfo")
    suspend fun fetchInfo(): FetchInfoRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInfo(fetchInfoRoomEntity: FetchInfoRoomEntity)

    @Query("SELECT * FROM userHealth")
    suspend fun fetchUserHealth(): FetchUserHealthRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserHealth(fetchUserHealthRoomEntity: FetchUserHealthRoomEntity)

    @Query("SELECT * FROM authInfo")
    suspend fun fetchAuthInfo(): FetchAuthInfoRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuthInfo(fetchAuthInfoRoomEntity: FetchAuthInfoRoomEntity)
}