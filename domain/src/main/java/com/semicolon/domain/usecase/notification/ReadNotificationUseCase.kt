package com.semicolon.domain.usecase.notification

import com.semicolon.domain.repository.NotificationRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class ReadNotificationUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) : UseCase<Int, Unit>() {

    override suspend fun execute(notificationId: Int): Unit =
        notificationRepository.readNotification(notificationId)
}