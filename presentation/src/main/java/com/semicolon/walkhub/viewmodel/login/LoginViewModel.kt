package com.semicolon.walkhub.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.exception.BadRequestException
import com.semicolon.domain.exception.NoInternetException
import com.semicolon.domain.exception.NotFoundException
import com.semicolon.domain.exception.UnauthorizedException
import com.semicolon.domain.param.user.PostUserSignInParam
import com.semicolon.domain.usecase.user.PostUserSignInUseCase
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
                withContext(Dispatchers.Default) {
                    postUserSignInUseCase.execute(PostUserSignInParam(accountId, password))
                }
            }.onSuccess {
                event(Event.Success(true))
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷 연결을 하시고 로그인을 시도해주세요."))
                    is BadRequestException -> event(Event.ErrorMessage("잘못된 형식의 요청입니다."))
                    is UnauthorizedException -> event(Event.ErrorMessage("잘못된 형식의 토큰입니다."))
                    is NotFoundException -> event(Event.ErrorMessage("아이디나 비밀번호가 틀립니다."))
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
        data class Success(val state: Boolean): Event()
    }
}