package com.semicolon.walkhub.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.exception.NoInternetException
import com.semicolon.domain.param.user.PostUserSignInParam
import com.semicolon.domain.usecase.user.PostUserSignInUseCase
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postUserSignInUseCase: PostUserSignInUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun postLogin(accountId: String, password: String) {
        viewModelScope.launch() {
            kotlin.runCatching {
                async {
                    postUserSignInUseCase.execute(PostUserSignInParam(accountId, password))
                }.await()
            }.onSuccess {
                event(Event.Success(""))
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷 연결을 하시고 로그인을 시도해주세요."))
                    else -> event(Event.ErrorMessage("로그인 정보가 틀립니다."))
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
        data class Success(val message: String): Event()
    }
}