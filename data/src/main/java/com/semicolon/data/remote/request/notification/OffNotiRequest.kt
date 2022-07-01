package com.semicolon.data.remote.request.notification

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.enums.NotificationType

data class OffNotiRequest(
    @SerializedName("type") val type: NotificationType,
    @SerializedName("user_id_list") val userIdList: List<Int>
)