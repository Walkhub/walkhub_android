package com.semicolon.data.remote.request.users

data class UpdateProfileRequest(
    val birthdat: Int,
    val name: String,
    val profile_url: String,
    val sex: String
)