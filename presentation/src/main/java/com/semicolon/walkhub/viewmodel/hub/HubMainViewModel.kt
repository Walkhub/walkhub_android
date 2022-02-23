package com.semicolon.walkhub.viewmodel.hub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.enum.DateType
import com.semicolon.walkhub.ui.hub.model.HubSchoolRankData
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

    fun fetchSchoolRank(dateType: DateType) {
        val mySchool = HubSchoolRankData.MySchool(1, "대덕소프트웨어마이스터고등학교", "https://cdn.discordapp.com/attachments/813414875241513010/945222326285369374/R9XM6uqHeMKjQAAAABJRU5ErkJggg.png", 1123213, 1, 1)
        val otherSchool: List<HubSchoolRankData.OtherSchool> = listOf(
            HubSchoolRankData.OtherSchool(2, "대전문지중학교", 1, 1312, "https://cdn.discordapp.com/attachments/813414875241513010/945222326285369374/R9XM6uqHeMKjQAAAABJRU5ErkJggg.png", 1123123),
            HubSchoolRankData.OtherSchool(3, "대전가오고등학교", 2, 151, "https://cdn.discordapp.com/attachments/813414875241513010/945222326285369374/R9XM6uqHeMKjQAAAABJRU5ErkJggg.png", 14124),
            HubSchoolRankData.OtherSchool(4, "대전가오중학교", 3, 918, "https://cdn.discordapp.com/attachments/813414875241513010/945222326285369374/R9XM6uqHeMKjQAAAABJRU5ErkJggg.png", 11614)
        )
        val data = HubSchoolRankData(mySchool, otherSchool)

        event(Event.FetchSchoolRank(data))
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class FetchSchoolRank(val hubSchoolRankData: HubSchoolRankData) : Event()
    }
}