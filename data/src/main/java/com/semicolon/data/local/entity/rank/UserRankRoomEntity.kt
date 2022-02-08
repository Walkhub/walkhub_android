package com.semicolon.data.local.entity.rank

import androidx.room.Embedded
import androidx.room.Entity
import com.semicolon.domain.entity.rank.UserRankEntity

@Entity(tableName = "userRank")
data class UserRankRoomEntity(
    @Embedded val rankList: List<UserRank>
) {
    data class UserRank(
        val classNum: Int,
        val grade: Int,
        val name: String,
        val profileImageUrl: String,
        val rank: Int,
        val userId: Int,
        val walkCount: Int
    )

    fun UserRank.toEntity() =
        UserRankEntity.UserRank(
            classNum = classNum,
            grade = grade,
            name = name,
            profileImageUrl = profileImageUrl,
            rank = rank,
            userId = userId,
            walkCount = walkCount
        )
}

fun UserRankRoomEntity.toEntity() =
    UserRankEntity(
        rankList = rankList.map { it.toEntity() }
    )

fun UserRankEntity.UserRank.toDbEntity() =
    UserRankRoomEntity.UserRank(
        classNum = classNum,
        grade = grade,
        name = name,
        profileImageUrl = profileImageUrl,
        rank = rank,
        userId = userId,
        walkCount = walkCount
    )

fun UserRankEntity.toDbEntity() =
    UserRankRoomEntity(
        rankList = rankList.map { it.toDbEntity() }
    )