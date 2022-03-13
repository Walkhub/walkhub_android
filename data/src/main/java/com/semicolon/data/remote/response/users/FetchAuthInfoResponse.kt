package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.users.FetchAuthInfoEntity

data class FetchAuthInfoResponse(
    @SerializedName("account_id") val accountId: String,
    @SerializedName("phone_number") val phoneNumber: String
)

fun FetchAuthInfoResponse.toEntity() =
    FetchAuthInfoEntity(
        accountId = accountId,
        phoneNumber = phoneNumber
    )