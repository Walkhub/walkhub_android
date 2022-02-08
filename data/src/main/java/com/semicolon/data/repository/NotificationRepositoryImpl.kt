package com.semicolon.data.repository

import com.semicolon.data.local.datasource.LocalNotificationDataSource
import com.semicolon.data.remote.datasource.RemoteNotificationDataSource
import com.semicolon.data.remote.response.notification.toEntity
import com.semicolon.data.util.OfflineCacheUtil
import com.semicolon.domain.entity.notification.NotificationListEntity
import com.semicolon.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val localNotificationDataSource: LocalNotificationDataSource,
    private val remoteNotificationDataSource: RemoteNotificationDataSource
) : NotificationRepository {

    override suspend fun fetchNotificationList(): Flow<List<NotificationListEntity>> =
        OfflineCacheUtil<List<NotificationListEntity>>()
            .remoteData { remoteNotificationDataSource.fetchNotificationList().toEntity() }
            .localData { localNotificationDataSource.fetchNotificationList() }
            .compareData { localData, remoteData -> localData.containsAll(remoteData) }
            .doOnNeedRefresh { localNotificationDataSource.saveNotificationList(it) }
            .createFlow()

    override suspend fun patchNotificationIsRead(notificationId: Int) =
        remoteNotificationDataSource.patchNotificationIsRead(notificationId)

}
