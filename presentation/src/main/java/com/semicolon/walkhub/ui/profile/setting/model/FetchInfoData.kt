package com.semicolon.walkhub.ui.profile.setting.model

import com.google.gson.annotations.SerializedName

data class FetchInfoData(
    val name: String,
    val profileImageUrl: String,
    val schoolName: String,
    val grade: Int,
    val classNum: Int
)
