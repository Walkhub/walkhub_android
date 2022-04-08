package com.semicolon.walkhub.viewmodel.profile.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoticeSettingViewModel @Inject constructor(
    //TODO: 아직 켜기 끄기 Notification 부분에 구현이 안되있음 UseCase만들어지는대로 만들어야 함
): ViewModel() {

    private val _eventFlow = MutableEventFlow<SettingViewModel.Event>()
    val eventFlow = _eventFlow.asEventFlow()


    private fun event(event: SettingViewModel.Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }
}