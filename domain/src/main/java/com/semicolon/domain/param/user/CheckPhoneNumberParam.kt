package com.semicolon.domain.param.user

data class CheckPhoneNumberParam(
    var phoneNumber: String,
    var authCode: String
)
