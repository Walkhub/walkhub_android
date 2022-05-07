package com.semicolon.walkhub.viewmodel.notification

import android.app.Notification
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.notification.NotificationEntity
import com.semicolon.domain.exception.NoInternetException
import com.semicolon.domain.exception.NotFoundException
import com.semicolon.domain.usecase.notification.FetchNotificationListUseCase
import com.semicolon.walkhub.ui.notification.model.NotificationData
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class NotificationViewModel (
    private val notificationUseCase: FetchNotificationListUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun fetchNotification() {
        viewModelScope.launch {
            kotlin.runCatching {
                notificationUseCase.execute(Unit).collect() {
                    event(Event.NotificationValue(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷 연결을 확인해주세요."))
                    is NotFoundException -> event(Event.ErrorMessage("알림이 존제하지 않습니다."))
                    is NullPointerException -> event(Event.ErrorMessage("값이 존재하지 않습니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    fun NotificationEntity.toData() =
        NotificationData(
            notificationValue.map { it.toData() }
        )

    fun NotificationEntity.NotificationValue.toData() =
        NotificationData.NotificationValue(
            notificationId = notificationId,
            title = title,
            content = content,
            data = data,
            type = type,
            createAt = createAt,
            isRead = isRead,
            writer = writer.toData()
        )

    fun NotificationEntity.NotificationValue.Writer.toData() =
        NotificationData.NotificationValue.Writer(
            writerId = writerId,
            writerName = writerName,
            writerImage = writerImage
        )

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class NotificationValue(val notificationData: NotificationData?) : Event()
        data class ErrorMessage(val message: String) : Event()
    }
}