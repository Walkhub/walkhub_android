package com.semicolon.domain.usecase.notification

import com.semicolon.domain.param.notifications.SwitchOnNotificationsParam
import com.semicolon.domain.repository.NotificationRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class PatchSwitchOnNotificationUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) : UseCase<SwitchOnNotificationsParam, Unit>() {
    override suspend fun execute(data: SwitchOnNotificationsParam): Unit =
        notificationRepository.switchOnNotification(data)

}