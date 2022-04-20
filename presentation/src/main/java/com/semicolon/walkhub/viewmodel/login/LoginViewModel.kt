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
                event(Event.LoginSuccess)
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.NoInternet)
                    is BadRequestException -> event(Event.BadRequest)
                    is UnauthorizedException -> event(Event.Unauthorized)
                    is NotFoundException -> event(Event.WrongAccount)
                    else -> event(Event.UnknownError)
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
        object LoginSuccess : Event()
        object Unauthorized : Event()
        object BadRequest : Event()
        object WrongAccount : Event()
        object NoInternet : Event()
        object UnknownError : Event()
    }
}