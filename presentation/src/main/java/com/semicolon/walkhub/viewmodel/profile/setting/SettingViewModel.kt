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
                event(Event.LogoutSuccess)
            }.onFailure {
                event(Event.Failed)
            }
        }
    }


 private fun event(event: Event) {
    viewModelScope.launch {
        _eventFlow.emit(event)
    }
}
    sealed class Event {
        object LogoutSuccess : Event()
        object Failed : Event()
    }
}


