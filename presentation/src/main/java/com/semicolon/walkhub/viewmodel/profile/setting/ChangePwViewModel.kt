package com.semicolon.walkhub.viewmodel.profile.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.exception.BadRequestException
import com.semicolon.domain.exception.ForbiddenException
import com.semicolon.domain.exception.NoInternetException
import com.semicolon.domain.exception.UnauthorizedException
import com.semicolon.domain.param.user.VerifyPasswordParam
import com.semicolon.domain.usecase.user.VerifyPasswordUseCase
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.NullPointerException
import javax.inject.Inject

@HiltViewModel
class ChangePwViewModel @Inject constructor(
    private val verifyPasswordUseCase: VerifyPasswordUseCase
): ViewModel() {


    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun verifyPassword(verifyPasswordParam: VerifyPasswordParam) {
        viewModelScope.launch {
            kotlin.runCatching {
                verifyPasswordUseCase.execute(verifyPasswordParam)
            }.onSuccess {
                event(Event.SuccessVerify)
            }.onFailure {
                when (it) {
                    is BadRequestException -> event(Event.ErrorMessage("요청 형식을 식별할 수 없습니다."))
                    is UnauthorizedException -> event(Event.ErrorMessage("비밀번호가 틀렸어요."))
                    is NoInternetException -> event(Event.ErrorMessage("인터넷에 연결되어있지 않습니다."))
                    is NullPointerException -> event(Event.ErrorMessage("비밀번호를 입력해주세요."))
                    is ForbiddenException -> event(Event.ErrorMessage("403"))
                    else -> event(Event.ErrorMessage("에러가 발생했습니다."))
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
        object SuccessVerify: Event()
        data class ErrorMessage(val message: String) : Event()
    }
}