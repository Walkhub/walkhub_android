package com.semicolon.walkhub.viewmodel.hub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.exception.ConflictException
import com.semicolon.domain.exception.NoInternetException
import com.semicolon.domain.param.user.SignUpClassParam
import com.semicolon.domain.usecase.user.CheckClassCodeUseCase
import com.semicolon.domain.usecase.user.SignUpClassUseCase
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpClassViewModel @Inject constructor(
    private val signUpClassUseCase: SignUpClassUseCase,
    private val checkClassCodeUseCase: CheckClassCodeUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun signUpClass(classCode: String, number: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                signUpClassUseCase.execute(SignUpClassParam(classCode, number))
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷을 사용할 수 없습니다"))
                    is ConflictException -> event(Event.ErrorMessage("이미 반에 가입되어 있습니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }.onSuccess {
                event(Event.Success(true))
            }
        }
    }

    fun checkClassCode(code: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                checkClassCodeUseCase.execute(code)
            }.onFailure {
                event(Event.ClassCodeState(false))
            }.onSuccess {
                event(Event.ClassCodeState(true))
            }
        }
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class ClassCodeState(val code: Boolean) : Event()
        data class Success(val state: Boolean) : Event()
        data class ErrorMessage(val message: String) : Event()
    }
}