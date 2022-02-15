package com.semicolon.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.semicolon.data.local.entity.rank.*


@Dao
interface RankDao {
    @Query("SELECT * FROM ourschoolUserRank")
    suspend fun fetchOurSchoolUserRank(): OurSchoolUserRankRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOurSchoolUserRank(ourSchoolUserRanks: OurSchoolUserRankRoomEntity)

    @Query("SELECT * FROM userRank")
    suspend fun fetchUserRank(): UserRankRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserRank(userRank: UserRankRoomEntity)

    @Query("SELECT * FROM schoolRank")
    suspend fun fetchSchoolRank(): SchoolRankRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchoolRank(schoolRank: SchoolRankRoomEntity)

    @Query("SELECT * FROM searchUser")
    suspend fun searchUser(): SearchUserRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchUser(searchUser: SearchUserRoomEntity)

    @Query("SELECT * FROM searchSchool")
    suspend fun searchSchool(): SearchSchoolRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchSchool(searchSchool: SearchSchoolRoomEntity)
}