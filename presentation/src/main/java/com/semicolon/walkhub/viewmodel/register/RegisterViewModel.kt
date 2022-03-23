package com.semicolon.walkhub.viewmodel.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.exception.basic.NoInternetException
import com.semicolon.domain.param.user.PostUserSignUpParam
import com.semicolon.domain.usecase.user.PostUserSignUpUseCase
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import com.semicolon.walkhub.viewmodel.login.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val postUserSignUpUseCase: PostUserSignUpUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun postRegister() {
        viewModelScope.launch() {
            kotlin.runCatching {
                withContext(Dispatchers.Default) {

                }
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷 연결을 하시고 회원가입을 시도해주세요."))
                    else -> event(Event.ErrorMessage("알 수 없는 오류가 발생하였습니다."))
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
        data class ErrorMessage(val message: String) : Event()
    }
}