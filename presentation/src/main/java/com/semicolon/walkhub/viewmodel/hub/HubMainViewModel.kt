package com.semicolon.walkhub.viewmodel.hub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.rank.SchoolRankEntity
import com.semicolon.domain.enum.DateType
import com.semicolon.domain.usecase.rank.FetchSchoolRankUseCase
import com.semicolon.walkhub.ui.hub.model.HubSchoolRankData
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HubMainViewModel @Inject constructor(
    private val fetchSchoolRankUseCase: FetchSchoolRankUseCase
): ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun fetchSchoolRank(dateType: DateType) {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchSchoolRankUseCase.execute(dateType).collect {
                    event(Event.FetchSchoolRank(it.toData()))
                }
            }
        }

    }

    private fun SchoolRankEntity.MySchoolRank.toData() =
        HubSchoolRankData.MySchool (
            schoolId = schoolId,
            name = name,
            logoImageUrl = logoImageUrl,
            walkCount = walkCount,
            grade = grade,
            classNum = classNum
        )

    private fun SchoolRankEntity.SchoolRank.toData() =
        HubSchoolRankData.OtherSchool (
            schoolId = schoolId,
            name = name,
            ranking = ranking,
            studentCount = studentCount,
            logoImageUrl = logoImageUrl,
            walkCount = walkCount
        )

    private fun SchoolRankEntity.toData() =
        HubSchoolRankData(
            mySchoolRank.toData(),
            schoolList = schoolRankList.map { it.toData() }
        )

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class FetchSchoolRank(val hubSchoolRankData: HubSchoolRankData) : Event()
    }
}