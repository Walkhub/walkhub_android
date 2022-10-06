package com.semicolon.domain.param.notifications

import com.semicolon.domain.enums.NotificationType

data class SwitchOffNotificationsParam (
    val userId: Int,
    val type: NotificationType
)