package com.semicolon.data.local.entity.rank

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.rank.SearchUserEntity

@Entity(tableName = "searchUser")
data class SearchUserRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @Embedded val userList: List<UserInfo>
) {
    data class UserInfo(
        val classNum: Int,
        val grade: Int,
        val name: String,
        val profileImageUrl: String,
        val rank: Int,
        val userId: Int,
        val walkCount: Int
    )

    fun UserInfo.toEntity() =
        SearchUserEntity.UserInfo(
            classNum = classNum,
            grade = grade,
            name = name,
            profileImageUrl = profileImageUrl,
            rank = rank,
            userId = userId,
            walkCount = walkCount
        )
}

fun SearchUserRoomEntity.toEntity() =
    SearchUserEntity(
        userList = userList.map { it.toEntity() }
    )

fun SearchUserEntity.UserInfo.toDbEntity() =
    SearchUserRoomEntity.UserInfo(
        classNum = classNum,
        grade = grade,
        name = name,
        profileImageUrl = profileImageUrl,
        rank = rank,
        userId = userId,
        walkCount = walkCount
    )

fun SearchUserEntity.toDbEntity() =
    SearchUserRoomEntity(
        userList = userList.map { it.toDbEntity() }
    )
