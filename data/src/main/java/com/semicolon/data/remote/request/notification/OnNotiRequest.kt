package com.semicolon.data.remote.request.notification

import com.google.gson.annotations.SerializedName

data class OnNotiRequest (
    @SerializedName("type") val type: String,
    @SerializedName("user_id_list") val userIdList: List<Int>
)