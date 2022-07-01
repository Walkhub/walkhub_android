package com.semicolon.walkhub.viewmodel.profile.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.notification.NotificationStatusEntity
import com.semicolon.domain.enums.NotificationType
import com.semicolon.domain.exception.BadRequestException
import com.semicolon.domain.exception.ForbiddenException
import com.semicolon.domain.exception.NoInternetException
import com.semicolon.domain.exception.UnauthorizedException
import com.semicolon.domain.param.notifications.SwitchOffNotificationsParam
import com.semicolon.domain.param.notifications.SwitchOnNotificationsParam
import com.semicolon.domain.usecase.notification.NotificationStatusUseCase
import com.semicolon.domain.usecase.notification.PatchSwitchOffNotificationUseCase
import com.semicolon.domain.usecase.notification.PatchSwitchOnNotificationUseCase
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoticeSettingViewModel @Inject constructor(
    private val switchOnNotificationUseCase: PatchSwitchOnNotificationUseCase,
    private val switchOffNotificationUseCase: PatchSwitchOffNotificationUseCase,
    private val notificationStatusUseCase: NotificationStatusUseCase,
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    companion object {
        var userId: Int = 0
    }


    fun patchSwitchOn(userId: Int, type: NotificationType) {
        viewModelScope.launch {
            kotlin.runCatching {
                switchOnNotificationUseCase.execute(SwitchOnNotificationsParam(userId, type))
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷에 연결되어있지 않습니다."))
                    is BadRequestException -> event(Event.ErrorMessage("요청이 잘못되었습니다. 입력 값을 확인해주세요."))
                    is UnauthorizedException -> event(Event.ErrorMessage("세션이 만료되었습니다. 다시 시도해주세요."))
                    else -> event(Event.ErrorMessage("에러가 발생했습니다."))
                }
            }
        }
    }

    fun patchSwitchOff(userId: Int, type: NotificationType) {
        viewModelScope.launch {
            kotlin.runCatching {
                switchOffNotificationUseCase.execute(SwitchOffNotificationsParam(userId, type))
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷에 연결되어있지 않습니다."))
                    is BadRequestException -> event(Event.ErrorMessage("요청이 잘못되었습니다. 입력 값을 확인해주세요."))
                    is UnauthorizedException -> event(Event.ErrorMessage("세션이 만료되었습니다. 다시 시도해주세요."))
                    else -> event(Event.ErrorMessage("에러가 발생했습니다."))
                }
            }
        }
    }

    fun notificationStatus() {
        viewModelScope.launch {
            kotlin.runCatching {
                notificationStatusUseCase.execute(Unit).collect() {
                    event(Event.WhetherNotification(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is UnauthorizedException -> event(Event.ErrorMessage("세션이 만료되었습니다. 다시 시도해주세요."))
                    is ForbiddenException -> event(Event.ErrorMessage("권한이 없습니다."))
                }
            }
        }
    }

    fun NotificationStatusEntity.toData() =
        NotificationStatusEntity(
            topicWhetherList.map { it.toData() }
        )

    fun NotificationStatusEntity.TopicWhether.toData() =
        NotificationStatusEntity.TopicWhether(
            isSubscribe = isSubscribe,
            title = title,
            topicId = topicId
        )

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class WhetherNotification(val notificationStatusEntity: NotificationStatusEntity) : Event()
        data class ErrorMessage(val message: String) : Event()
    }
}