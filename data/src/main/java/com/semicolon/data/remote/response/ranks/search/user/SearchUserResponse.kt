package com.semicolon.data.remote.response.ranks.search.user

import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @SerializedName("user_list") val users: List<UserInfo>
)