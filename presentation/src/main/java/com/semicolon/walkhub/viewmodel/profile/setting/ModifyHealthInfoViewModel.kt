package com.semicolon.walkhub.viewmodel.profile.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.users.FetchUserHealthEntity
import com.semicolon.domain.exception.BadRequestException
import com.semicolon.domain.exception.NoInternetException
import com.semicolon.domain.exception.UnauthorizedException
import com.semicolon.domain.param.user.PatchUserHealthParam
import com.semicolon.domain.usecase.user.FetchUserHealthUseCase
import com.semicolon.domain.usecase.user.PatchUserHealthUseCase
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModifyHealthInfoViewModel @Inject constructor(
    private val patchUserHealthUseCase: PatchUserHealthUseCase,
    private val fetchUserHealthUseCase: FetchUserHealthUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()


    fun fetchUserHealth() {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchUserHealthUseCase.execute(Unit).collect {
                    event(Event.FetchUserHealth(it.toData()))
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

    fun patchUserHealth(height: Double, weight: Int, sex: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                patchUserHealthUseCase.execute(PatchUserHealthParam(height, weight, sex))
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

    private fun FetchUserHealthEntity.toData() =
        FetchUserHealthEntity(
            height = height,
            weight = weight,
            sex = sex
        )

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class FetchUserHealth(val fetchUserHealthData: FetchUserHealthEntity) : Event()
        data class ErrorMessage(val message: String) : Event()
    }

}