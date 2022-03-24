package com.semicolon.domain.param.user

data class VerifyPhoneNumberSignUpParam(
    val phoneNumber: String,
    val authCode: String
)

