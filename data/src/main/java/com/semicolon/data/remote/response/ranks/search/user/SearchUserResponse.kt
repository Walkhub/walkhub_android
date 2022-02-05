package com.semicolon.data.remote.response.ranks.search.user

import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @SerializedName("user_list") val userList: List<UserInfo>
) {
    data class UserInfo(
        @SerializedName("class_num") val classNum: Int,
        @SerializedName("grade") val grade: Int,
        @SerializedName("name") val name: String,
        @SerializedName("profile_image_url") val profileImageUrl: String,
        @SerializedName("rank") val rank: Int,
        @SerializedName("user_id") val userId: Int,
        @SerializedName("walk_count") val walkCount: Int
    )
}