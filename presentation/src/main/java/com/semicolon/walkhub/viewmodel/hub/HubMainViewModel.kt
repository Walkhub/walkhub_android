package com.semicolon.walkhub.viewmodel.hub

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.rank.SchoolRankEntity
import com.semicolon.domain.enum.DateType
import com.semicolon.domain.exception.basic.NoInternetException
import com.semicolon.domain.exception.basic.UnknownException
import com.semicolon.domain.param.user.PostUserSignInParam
import com.semicolon.domain.usecase.rank.FetchSchoolRankUseCase
import com.semicolon.domain.usecase.user.PostUserSignInUseCase
import com.semicolon.walkhub.R
import com.semicolon.walkhub.ui.hub.model.HubSchoolRankData
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HubMainViewModel @Inject constructor(
    private val fetchSchoolRankUseCase: FetchSchoolRankUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun fetchSchoolRank(dateType: DateType) {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchSchoolRankUseCase.execute(dateType).collect {
                    event(Event.FetchSchoolRank(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷을 사용할 수 없습니다"))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다.\\n잠시 후 다시 시도해주세요."))
                }
            }
        }
    }

    private fun SchoolRankEntity.MySchoolRank.toData() =
        HubSchoolRankData.MySchool(
            schoolId = schoolId,
            name = name,
            logoImageUrl = logoImageUrl,
            walkCount = walkCount,
            grade = grade,
            classNum = classNum
        )

    private fun SchoolRankEntity.SchoolRank.toData() =
        HubSchoolRankData.OtherSchool(
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
        data class ErrorMessage(val message: String) : Event()
    }
}