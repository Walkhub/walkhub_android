package com.semicolon.data.local.entity.rank

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.rank.UserRankEntity

@Entity(tableName = "userRank")
data class UserRankRoomEntity(
    @PrimaryKey (autoGenerate = true) var id: Int = 0,
    @Embedded val rankList: List<UserRank>
) {
    data class UserRank(
        val userId: Int,
        val name: String,
        val grade: Int,
        val classNum: Int,
        val number: Int,
        val ranking: Int,
        val profileImageUrl: String,
        val walkCount: Int
    )

    fun UserRank.toEntity() =
        UserRankEntity.UserRank(
            userId = userId,
            name = name,
            grade = grade,
            classNum = classNum,
            number = number,
            ranking = ranking,
            profileImageUrl = profileImageUrl,
            walkCount = walkCount
        )
}

fun UserRankRoomEntity.toEntity() =
    UserRankEntity(
        rankList = rankList.map { it.toEntity() }
    )

fun UserRankEntity.UserRank.toDbEntity() =
    UserRankRoomEntity.UserRank(
        userId = userId,
        name = name,
        grade = grade,
        classNum = classNum,
        number = number,
        ranking = ranking,
        profileImageUrl = profileImageUrl,
        walkCount = walkCount
    )

fun UserRankEntity.toDbEntity() =
    UserRankRoomEntity(
        rankList = rankList.map { it.toDbEntity() }
    )