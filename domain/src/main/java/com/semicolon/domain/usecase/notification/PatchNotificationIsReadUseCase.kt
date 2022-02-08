package com.semicolon.domain.usecase.notification

import com.semicolon.domain.repository.NotificationRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class PatchNotificationIsReadUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) : UseCase<Int, Unit>() {

    override suspend fun execute(data: Int): Unit =
        notificationRepository.patchNotificationIsRead(data)
}