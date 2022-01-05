package com.semicolon.data.remote.response

data class MyRank(
    val name: String,
    val profile_image_url: String,
    val rank: Int,
    val user_id: Int,
    val walk_count: Int
)