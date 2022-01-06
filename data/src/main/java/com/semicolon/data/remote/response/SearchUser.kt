package com.semicolon.data.remote.response

import com.google.gson.annotations.SerializedName

data class SearchUser(
    @SerializedName("user_list") val userList: List<User>
)