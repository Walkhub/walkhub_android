package com.semicolon.walkhub.ui.profile.setting.model

import com.google.gson.annotations.SerializedName

data class FetchUserHealthData(
    val height: Double,
    val weight: Double,
    val sex: String
)
