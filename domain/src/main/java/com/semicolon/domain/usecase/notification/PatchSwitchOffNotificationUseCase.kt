package com.semicolon.domain.usecase.notification

import com.semicolon.domain.enums.NotificationType
import com.semicolon.domain.repository.NotificationRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class PatchSwitchOffNotificationUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) : UseCase<NotificationType, Unit>() {
    override suspend fun execute(data: NotificationType): Unit =
        notificationRepository.switchOffNotification(data)

}