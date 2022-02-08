package com.semicolon.domain.usecase.notification

import com.semicolon.domain.entity.notification.NotificationListEntity
import com.semicolon.domain.repository.NotificationRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchNotificationListUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) : UseCase<Unit, Flow<List<NotificationListEntity>>>() {

    override suspend fun execute(data: Unit): Flow<List<NotificationListEntity>> =
        notificationRepository.fetchNotificationList()
}