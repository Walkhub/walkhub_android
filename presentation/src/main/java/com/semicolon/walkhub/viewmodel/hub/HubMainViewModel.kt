package com.semicolon.walkhub.viewmodel.hub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.walkhub.ui.hub.model.HubSchoolRank
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HubMainViewModel @Inject constructor(
): ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun fetchSchoolRank() {
        val mySchool = HubSchoolRank.MySchool(1, "대덕소프트웨어마이스터고등학교", "http://goo.gl/gEgYUd", 1123213, 1, 1)
        val otherSchool: List<HubSchoolRank.OtherSchool> = listOf(
            HubSchoolRank.OtherSchool(2, "대전문지중학교", 1, 1312, "http://goo.gl/gEgYUd", 1123123),
            HubSchoolRank.OtherSchool(3, "대전가오고등학교", 2, 151, "http://goo.gl/gEgYUd", 14124),
            HubSchoolRank.OtherSchool(4, "대전가오중학교", 3, 918, "http://goo.gl/gEgYUd", 11614)
        )
        val data = HubSchoolRank(mySchool, otherSchool)

        event(Event.FetchSchoolRank(data))
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class FetchSchoolRank(val hubSchoolRank: HubSchoolRank) : Event()
    }
}