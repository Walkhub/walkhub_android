package com.semicolon.walkhub.viewmodel.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.exception.*
import com.semicolon.domain.usecase.user.CheckAccountOverlapUseCase
import com.semicolon.domain.usecase.user.VerifyUserPhoneNumberUseCase
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val checkAccountOverlapUseCase: CheckAccountOverlapUseCase,
    private val verifyUserPhoneNumberUseCase: VerifyUserPhoneNumberUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun checkId(id: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                checkAccountOverlapUseCase.execute(id)
            }.onSuccess {
                event(Event.SuccessId(true))
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷 연결을 확인해주세요."))
                    is ConflictException -> event(Event.ErrorMessage("이미 존재하는 아이디입니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 오류가 발생하였습니다."))
                }
            }
        }
    }

    fun verifyPhone(phone_number: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                verifyUserPhoneNumberUseCase.execute(phone_number)
            }.onSuccess {
                event(Event.SuccessVerityPhone(true))
            }.onFailure {
                when(it){
                    is BadRequestException -> event(Event.ErrorMessage("전화번호 형식이 올바르지 않습니다."))
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
        data class SuccessId(val state: Boolean): Event()
        data class SuccessVerityPhone(val state: Boolean): Event()
        data class ErrorMessage(val message: String) : Event()
    }
}