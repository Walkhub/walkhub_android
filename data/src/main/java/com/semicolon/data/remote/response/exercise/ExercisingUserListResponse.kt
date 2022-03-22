package com.semicolon.data.remote.response.exercise

import com.google.gson.annotations.SerializedName

data class ExercisingUserListResponse(
    @SerializedName("user_list") val userList: List<User>
) {
    data class User(
        @SerializedName("user_id") val userId: Int,
        @SerializedName("name") val name: String,
        @SerializedName("profile_image_url") val profileImageUrl: String
    )
}
