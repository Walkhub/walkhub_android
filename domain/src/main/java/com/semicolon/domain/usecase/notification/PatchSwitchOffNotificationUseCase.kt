package com.semicolon.domain.usecase.notification

import com.semicolon.domain.enums.NotificationType
import com.semicolon.domain.param.notifications.SwitchOffNotificationsParam
import com.semicolon.domain.repository.NotificationRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class PatchSwitchOffNotificationUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) : UseCase<SwitchOffNotificationsParam, Unit>() {
    override suspend fun execute(data: SwitchOffNotificationsParam): Unit =
        notificationRepository.switchOffNotification(data)

}