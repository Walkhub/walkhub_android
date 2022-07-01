package com.semicolon.domain.usecase.notification

import com.semicolon.domain.entity.notification.NotificationStatusEntity
import com.semicolon.domain.repository.NotificationRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotificationStatusUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) : UseCase<Unit, Flow<NotificationStatusEntity>>() {

    override suspend fun execute(data: Unit): Flow<NotificationStatusEntity> =
        notificationRepository.notificationStatus()
}