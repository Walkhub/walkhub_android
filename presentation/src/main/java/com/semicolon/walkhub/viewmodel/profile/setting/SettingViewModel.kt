package com.semicolon.walkhub.viewmodel.profile.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.usecase.user.LogOutUseCase
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val logOutUseCase: LogOutUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun logOut() {
        viewModelScope.launch {
            kotlin.runCatching {
               logOutUseCase.execute(Unit)
            }.onSuccess {
                event(Event.Success)
            }.onFailure {
                event(Event.Failed("인터넷에 연결되지 않았습니다."))
            }
        }
    }


 private fun event(event: Event) {
    viewModelScope.launch {
        _eventFlow.emit(event)
    }
}
    sealed class Event {
        object Success : Event()
        data class Failed(val message: String) : Event()
    }
}


