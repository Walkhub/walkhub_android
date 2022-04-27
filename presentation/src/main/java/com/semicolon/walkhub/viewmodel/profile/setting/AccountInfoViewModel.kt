package com.semicolon.walkhub.viewmodel.profile.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.users.FetchAuthInfoEntity
import com.semicolon.domain.exception.BadRequestException
import com.semicolon.domain.exception.NoInternetException
import com.semicolon.domain.exception.NotFoundException
import com.semicolon.domain.exception.UnauthorizedException
import com.semicolon.domain.param.user.PatchUserChangePasswordParam
import com.semicolon.domain.usecase.user.DeleteAccountUseCase
import com.semicolon.domain.usecase.user.FetchAuthInfoUseCase
import com.semicolon.domain.usecase.user.PatchUserChangePasswordUseCase
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountInfoViewModel @Inject constructor(
    private val patchUserChangePasswordUseCase: PatchUserChangePasswordUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase,
    private val fetchAuthInfoUseCase: FetchAuthInfoUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()


    fun fetchAuthInfo() {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchAuthInfoUseCase.execute(Unit).collect {
                    event(Event.FetchAuthInfo(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷에 연결되어있지 않습니다."))
                    is UnauthorizedException -> event(Event.ErrorMessage("세션이 만료되었습니다. 다시 시도해주세요."))
                    else -> event(Event.ErrorMessage("에러가 발생했습니다."))
                }
            }
        }
    }

    fun deleteAccount() {
        viewModelScope.launch {
            kotlin.runCatching {
                deleteAccountUseCase.execute(Unit)
            }.onFailure {
                when (it) {
                    is NotFoundException -> event(Event.ErrorMessage("요청하는 대상을 확인할 수 없습니다."))
                    is UnauthorizedException -> event(Event.ErrorMessage("세션이 만료되었습니다. 다시 시도해주세요."))
                    is NoInternetException -> event(Event.ErrorMessage("인터넷에 연결할 수 없습니다."))
                    else -> event(Event.ErrorMessage("에러가 발생했습니다."))
                }
            }
        }
    }

    fun patchUserChangePassword(
        password: String, newPassword: String
    ) {
        viewModelScope.launch {
            kotlin.runCatching {
                patchUserChangePasswordUseCase.execute(
                    PatchUserChangePasswordParam(
                        password, newPassword
                    )
                )
            }.onFailure {
                when (it) {
                    is BadRequestException -> event(Event.ErrorMessage("요청이 잘못되었습니다. 입력 값을 확인해주세요."))
                    is UnauthorizedException -> event(Event.ErrorMessage("세션이 만료되었습니다. 다시 시도해주세요."))
                    is NotFoundException -> event(Event.ErrorMessage("요청하는 대상을 확인할 수 없습니다."))
                    is NoInternetException -> event(Event.ErrorMessage("인터넷에 연결되어있지 않습니다."))
                    else -> event(Event.ErrorMessage("에러가 발생했습니다."))
                }
            }
        }
    }


    private fun FetchAuthInfoEntity.toData() =
        FetchAuthInfoEntity(
            accountId = accountId,
            phoneNumber = phoneNumber
        )

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class FetchAuthInfo(val fetchAuthInfoData: FetchAuthInfoEntity) : Event()
        data class ErrorMessage(val message: String) : Event()
    }
}