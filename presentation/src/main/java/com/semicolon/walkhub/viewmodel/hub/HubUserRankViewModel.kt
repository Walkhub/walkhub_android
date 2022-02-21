package com.semicolon.walkhub.viewmodel.hub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.rank.OurSchoolUserRankEntity
import com.semicolon.domain.entity.rank.UserRankEntity
import com.semicolon.domain.enum.DateType
import com.semicolon.walkhub.ui.hub.model.OurSchoolUserRankData
import com.semicolon.walkhub.ui.hub.model.UserRankData
import com.semicolon.walkhub.ui.hub.model.toData
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HubUserRankViewModel @Inject constructor(
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun fetchMySchoolUserRank(dateType: DateType) {
        val fakeData = OurSchoolUserRankEntity(
            OurSchoolUserRankEntity.Ranking(1, 1, "임세현", "http://goo.gl/gEgYUd", 41, 1, 415),
            listOf(
                OurSchoolUserRankEntity.Ranking(1, 1, "임세현", "http://goo.gl/gEgYUd", 41, 1, 415),
                OurSchoolUserRankEntity.Ranking(2, 2, "이용진", "http://goo.gl/gEgYUd", 51, 2, 1251),
                OurSchoolUserRankEntity.Ranking(3, 3, "유현명", "http://goo.gl/gEgYUd", 3, 3, 61123)
            )
        )

        event(Event.FetchMySchoolUserRank(fakeData.toData()))
    }

    fun fetchSchoolUserRank(dateType: DateType) {
        val fakeData = UserRankEntity(
            listOf(
                UserRankEntity.UserRank(1, "감자", 2, 3, 10, 1, "http://goo.gl/gEgYUd", 141),
                UserRankEntity.UserRank(2, "이용진", 2, 2, 15, 2, "http://goo.gl/gEgYUd", 1234),
                UserRankEntity.UserRank(3, "유현명", 3, 1, 23, 3, "http://goo.gl/gEgYUd", 1612)
            )
        )

        event(Event.FetchUserRank(fakeData.toData()))
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class FetchMySchoolUserRank(val ourSchoolUserRankData: OurSchoolUserRankData) : Event()
        data class FetchUserRank(val userRankData: UserRankData) : Event()
    }
}