package com.semicolon.data.remote.request.users

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.enums.SexType

data class UserSignUpRequest(
    @SerializedName("account_id") val accountId: String,
    @SerializedName("password") val password: String,
    @SerializedName("name") val name: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("height") val height: Double,
    @SerializedName("weight") val weight: Int,
    @SerializedName("sex") val sex: SexType,
    @SerializedName("school_id") val schoolId: Int,
    @SerializedName("auth_code") val authCode: String
)