package com.semicolon.domain.param.user

import com.semicolon.domain.enums.SexType

data class PostUserSignUpParam(
    var accountId: String,
    var password: String,
    var name: String,
    var phoneNumber: String,
    var height: Double,
    var weight: Int,
    val sex: SexType,
    var schoolId: Int,
    var authCode: String,
    var deviceToken: String
)

