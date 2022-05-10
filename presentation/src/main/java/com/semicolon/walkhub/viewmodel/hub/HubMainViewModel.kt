package com.semicolon.walkhub.viewmodel.hub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.rank.FetchMySchoolRankEntity
import com.semicolon.domain.entity.rank.SchoolRankAndSearchEntity
import com.semicolon.domain.enums.DateType
import com.semicolon.domain.exception.NoInternetException
import com.semicolon.domain.param.user.FetchSchoolRankAndSearchParam
import com.semicolon.domain.usecase.rank.FetchMySchoolRankUseCase
import com.semicolon.domain.usecase.rank.FetchSchoolRankAndSearchUseCase
import com.semicolon.walkhub.ui.hub.model.HubSchoolRankData
import com.semicolon.walkhub.ui.hub.model.MySchoolRankData
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.NullPointerException
import javax.inject.Inject

@HiltViewModel
class HubMainViewModel @Inject constructor(
    private val fetchSchoolRankAndSearchUseCase: FetchSchoolRankAndSearchUseCase,
    private val fetchMySchoolRankUseCase: FetchMySchoolRankUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun fetchSchoolRank(dateType: DateType) {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchSchoolRankAndSearchUseCase.execute(
                    FetchSchoolRankAndSearchParam(
                        null,
                        dateType.toString()
                    )
                ).collect {
                    event(Event.FetchSchoolRank(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.NoInternet)
                    is NullPointerException -> event(Event.NullPoint)
                    else -> event(Event.Unknown)
                }
            }
        }
    }

    fun fetchMySchool() {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchMySchoolRankUseCase.execute(Unit).collect {
                    event(Event.FetchMyRank(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.NoInternet)
                    is NullPointerException -> event(Event.NullPoint)
                    else -> event(Event.Unknown)
                }
            }
        }
    }

    private fun FetchMySchoolRankEntity.toData() =
        MySchoolRankData(
            schoolId = schoolId,
            name = name,
            logoImageUrl = logoImageUrl,
            grade = grade,
            classNum = classNum
        )

    private fun SchoolRankAndSearchEntity.SchoolRank.toData() =
        HubSchoolRankData.OtherSchool(
            schoolId = schoolId,
            schoolName = schoolName,
            ranking = ranking,
            logoImageUrl = logoImageUrl,
            walkCount = walkCount,
            userCount = userCount
        )

    private fun SchoolRankAndSearchEntity.toData() =
        HubSchoolRankData(
            schoolList = schoolRankList.map { it.toData() }
        )

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class FetchMyRank(val mySchoolRankData: MySchoolRankData) : Event()
        data class FetchSchoolRank(val hubSchoolRankData: HubSchoolRankData) : Event()
        object NullPoint : Event()
        object NoInternet : Event()
        object Unknown : Event()
    }
}