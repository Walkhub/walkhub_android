package com.semicolon.data.remote.response.ranks.search.user

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.rank.SearchUserEntity

data class SearchUserResponse(
    @SerializedName("user_list") val userList: List<UserInfo>
) {
    data class UserInfo(
        @SerializedName("class_num") val classNum: Int,
        @SerializedName("grade") val grade: Int,
        @SerializedName("name") val name: String,
        @SerializedName("profile_image_url") val profileImageUrl: String?,
        @SerializedName("ranking") val ranking: Int,
        @SerializedName("user_id") val userId: Int,
        @SerializedName("walk_count") val walkCount: Int
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

fun SearchUserResponse.toEntity() =
    SearchUserEntity(
        userList = userList.map { it.toEntity() }
    )