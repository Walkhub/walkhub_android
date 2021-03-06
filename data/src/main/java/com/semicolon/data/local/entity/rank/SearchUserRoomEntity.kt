package com.semicolon.data.local.entity.rank

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.rank.SearchUserEntity

@Entity(tableName = "searchUser")
data class SearchUserRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userList: List<UserInfo>
) {
    data class UserInfo(
        val classNum: Int,
        val grade: Int,
        val name: String,
        val profileImageUrl: String,
        val ranking: Int,
        val userId: Int,
        val walkCount: Int
    )

    fun UserInfo.toEntity() =
        SearchUserEntity.UserInfo(
            classNum = classNum,
            grade = grade,
            name = name,
            profileImageUrl = profileImageUrl,
            ranking = ranking,
            userId = userId,
            walkCount = walkCount
        )
}

fun SearchUserRoomEntity.toEntity() =
    SearchUserEntity(
        userList = userList.map { it.toEntity() }
    )

fun SearchUserEntity.UserInfo.toDbEntity() =
    profileImageUrl?.let {
        SearchUserRoomEntity.UserInfo(
            classNum = classNum,
            grade = grade,
            name = name,
            profileImageUrl = it,
            ranking = ranking,
            userId = userId,
            walkCount = walkCount
        )
    }

fun SearchUserEntity.toDbEntity() =
    userList.map { it.toDbEntity() }?.let {
        SearchUserRoomEntity(
            userList = it as List<SearchUserRoomEntity.UserInfo>
        )
    }
