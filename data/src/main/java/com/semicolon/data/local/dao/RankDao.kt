package com.semicolon.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.semicolon.data.local.entity.rank.*

@Dao
interface RankDao {

    @Query("SELECT * FROM mySchoolRank")
    suspend fun fetchMySchoolRank(): FetchMySchoolRankRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFetchMySchoolRank(fetchMySchoolRankRoomEntity: FetchMySchoolRankRoomEntity)

    @Query("SELECT * FROM ourschoolUserRank")
    suspend fun fetchOurSchoolUserRank(): OurSchoolUserRankRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOurSchoolUserRank(ourSchoolUserRanks: OurSchoolUserRankRoomEntity)

    @Query("SELECT * FROM userRank")
    suspend fun fetchUserRank(): UserRankRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserRank(userRank: UserRankRoomEntity)

    @Query("SELECT * FROM searchUser")
    suspend fun searchUser(): SearchUserRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchUser(searchUser: SearchUserRoomEntity)

    @Query("SELECT * FROM schoolRank")
    suspend fun fetchSchoolRankAndSearch(): SchoolRankAndSearchRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchoolAndSearch(schoolRankAndSearch: SchoolRankAndSearchRoomEntity)
}