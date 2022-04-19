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
                    is NoInternetException -> event(Event.ErrorMessage("인터넷을 사용할 수 없습니다"))
                    is NullPointerException -> event(Event.ErrorMessage("데이터가 없습니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    fun fetchMySchool(dateType: DateType) {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchMySchoolRankUseCase.execute(dateType.toString()).collect {
                    event(Event.FetchMyRank(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷을 사용항 수 없습니다."))
                    is NullPointerException -> event(Event.ErrorMessage("데이터가 없습니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    private fun FetchMySchoolRankEntity.toData() =
        MySchoolRankData(
            schoolId = mySchoolRank.schoolId,
            name = mySchoolRank.name,
            logoImageUrl = mySchoolRank.logoImageUrl,
            grade = mySchoolRank.grade,
            classNum = mySchoolRank.classNum
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
        data class ErrorMessage(val message: String) : Event()
    }
}