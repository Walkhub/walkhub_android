package com.semicolon.walkhub.viewmodel.hub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.rank.OurSchoolUserRankEntity
import com.semicolon.domain.entity.rank.UserRankEntity
import com.semicolon.domain.enum.DateType
import com.semicolon.walkhub.ui.hub.model.MySchoolUserRankData
import com.semicolon.walkhub.ui.hub.model.SchoolUserRankData
import com.semicolon.walkhub.ui.hub.model.toData
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HubSearchUserViewModel @Inject constructor(
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun fetchMySchoolUserRank(dateType: DateType) {
        val fakeImage = "https://cdn.discordapp.com/attachments/813414875241513010/945239959160373258/91087331-EB82A8EC84B1EC9D84EC9C84ED959C-EAB8B0EBB3B8-EC9584EBB094ED8380-ED9484EBA19CED9584-EC9584EC9DB4ECBD98-ED9A8CEC8389-EC82ACECA784-EC9E90EBA6AC-ED919CEC8B9C-EC9E90-EC9DBCEB9FACEC8AA4ED8AB8-EBB2A1ED84B0.png"
        val fakeData = OurSchoolUserRankEntity(
            OurSchoolUserRankEntity.Ranking(1, 1, "임세현", fakeImage, 41, 1, 415),
            listOf(
                OurSchoolUserRankEntity.Ranking(1, 1, "임세현", fakeImage, 41, 1, 415),
                OurSchoolUserRankEntity.Ranking(2, 2, "이용진", fakeImage, 51, 2, 1251),
                OurSchoolUserRankEntity.Ranking(3, 3, "유현명", fakeImage, 3, 3, 61123)
            )
        )

        event(Event.FetchMySchoolUserRank(fakeData.toData()))
    }

    fun fetchSchoolUserRank(dateType: DateType) {
        val fakeImage = "https://cdn.discordapp.com/attachments/813414875241513010/945239959160373258/91087331-EB82A8EC84B1EC9D84EC9C84ED959C-EAB8B0EBB3B8-EC9584EBB094ED8380-ED9484EBA19CED9584-EC9584EC9DB4ECBD98-ED9A8CEC8389-EC82ACECA784-EC9E90EBA6AC-ED919CEC8B9C-EC9E90-EC9DBCEB9FACEC8AA4ED8AB8-EBB2A1ED84B0.png"

        val fakeData = UserRankEntity(
            listOf(
                UserRankEntity.UserRank(1, "감자", 2, 3, 10, 1, fakeImage, 141),
                UserRankEntity.UserRank(2, "이용진", 2, 2, 15, 2, fakeImage, 1234),
                UserRankEntity.UserRank(3, "유현명", 3, 1, 23, 3, fakeImage, 1612)
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
        data class FetchMySchoolUserRank(val mySchoolUserRankData: MySchoolUserRankData) : Event()
        data class FetchUserRank(val userRankData: SchoolUserRankData) : Event()
    }
}