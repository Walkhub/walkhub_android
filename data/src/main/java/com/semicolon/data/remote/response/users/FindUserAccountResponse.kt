package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.users.FindUserAccountEntity

data class FindUserAccountResponse(
    @SerializedName("account_id") val accountId: String
)

fun FindUserAccountResponse.toEntity() =
    FindUserAccountEntity(
        accountId = accountId
    )