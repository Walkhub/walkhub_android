package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName

data class FindUserAccountsResponse(
    @SerializedName("account_id") val accountId: String
)