package com.semicolon.walkhub.viewmodel.hub

import androidx.lifecycle.*
import com.semicolon.domain.entity.rank.SearchSchoolEntity
import com.semicolon.domain.enum.MoreDateType
import com.semicolon.domain.exception.basic.NoInternetException
import com.semicolon.domain.exception.basic.NotFoundException
import com.semicolon.domain.param.rank.SearchSchoolParam
import com.semicolon.domain.usecase.rank.SearchSchoolUseCase
import com.semicolon.walkhub.ui.hub.model.SearchSchoolData
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class HubSearchSchoolViewModel @Inject constructor(
    private val searchSchoolUseCase: SearchSchoolUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun searchSchool(school: String, dateType: MoreDateType) {
        viewModelScope.launch {
            kotlin.runCatching {
                searchSchoolUseCase.execute(SearchSchoolParam(school, dateType)).collect() {
                    event(Event.SearchSchool(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷을 사용할 수 없습니다"))
                    is NotFoundException -> event(Event.ErrorMessage("요청하는 대상을 찾을 수 없습니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    fun SearchSchoolEntity.toData() =
        SearchSchoolData(
            schoolList.map { it.toData() }
        )

    fun SearchSchoolEntity.SchoolInfo.toData() =
        SearchSchoolData.SchoolInfo(
            schoolId = schoolId,
            schoolName = schoolName,
            ranking = ranking,
            logoImageUrl = logoImageUrl,
            walkCount = walkCount
        )

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class SearchSchool(val searchSchoolData: SearchSchoolData) : Event()
        data class ErrorMessage(val message: String) : Event()
    }
}