package com.semicolon.walkhub.viewmodel.profile.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.exception.*
import com.semicolon.domain.param.user.PatchUserChangePasswordParam
import com.semicolon.domain.usecase.user.PatchUserChangePasswordUseCase
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RealChangePwViewModel @Inject constructor(
    private val patchUserChangePasswordUseCase: PatchUserChangePasswordUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()


    fun patchUserChangePassword(patchUserChangePasswordParam: PatchUserChangePasswordParam) {
        viewModelScope.launch {
            kotlin.runCatching {
                patchUserChangePasswordUseCase.execute(
                    patchUserChangePasswordParam
                )
            }.onFailure {
                when (it) {
                    is ForbiddenException -> event(Event.ErrorMessage("권한이 없습니다."))
                    is BadRequestException -> event(Event.ErrorMessage("요청이 잘못되었습니다. 입력 값을 확인해주세요."))
                    is UnauthorizedException -> event(Event.ErrorMessage("세션이 만료되었습니다. 다시 시도해주세요."))
                    is NotFoundException -> event(Event.ErrorMessage("요청하는 대상을 확인할 수 없습니다."))
                    is NoInternetException -> event(Event.ErrorMessage("인터넷에 연결되어있지 않습니다."))
                    else -> event(Event.SuccessChange)
                }
            }
        }
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        object SuccessChange : Event()
        data class ErrorMessage(val message: String) : Event()
    }
}