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


    @Query("SELECT * FROM UserRank")
    suspend fun fetchUserRank(): UserRankRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserRank(userRank: UserRankRoomEntity)


    @Query("SELECT * FROM SchoolRank")
    suspend fun fetchSchoolRank(): SchoolRankRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchoolRank(schoolRank: SchoolRankRoomEntity)


    @Query("SELECT * FROM SearchUser")
    suspend fun searchUser(): SearchUserRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchUser(searchUser: SearchUserRoomEntity)


    @Query("SELECT * FROM SearchSchool")
    suspend fun searchSchool(): SearchSchoolRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchSchool(searchSchool: SearchSchoolRoomEntity)
}