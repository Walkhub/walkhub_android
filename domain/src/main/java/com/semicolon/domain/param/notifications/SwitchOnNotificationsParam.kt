package com.semicolon.domain.param.notifications

import com.semicolon.domain.enums.NotificationType

data class SwitchOnNotificationsParam (
        val userId: Int,
        val type: NotificationType
        )