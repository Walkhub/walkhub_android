package com.semicolon.walkhub.viewmodel

import androidx.lifecycle.*
import com.semicolon.domain.entity.rank.SearchSchoolEntity
import com.semicolon.domain.exception.NoInternetException
import com.semicolon.domain.exception.NotFoundException
import com.semicolon.domain.usecase.rank.SearchSchoolUseCase
import com.semicolon.walkhub.ui.hub.model.SearchSchoolData
import com.semicolon.walkhub.ui.register.model.SecondSearchSchoolData
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchSchoolViewModel @Inject constructor(
    private val searchSchoolUseCase: SearchSchoolUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private var searchJob: Job? = null

    //TODO: Type mismatch: inferred type is String but SearchSchoolParam was expected
    /*private fun searchSchool(school: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                searchSchoolUseCase.execute(school).collect() {
                    event(Event.SearchSchoolTwo(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷 연결을 확인해주세요."))
                    is NotFoundException -> event(Event.ErrorMessage("해당 학교는 존제하지 않습니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }*/

    /*fun searchSchoolDebounce(school: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            searchSchool(school)
        }
    }*/

    private fun SearchSchoolEntity.toData() =
        SecondSearchSchoolData(
            schoolList.map { it.toData() }
        )

    fun SearchSchoolEntity.SchoolInfo.toData() =
        SecondSearchSchoolData.SchoolInfo(
            schoolName = schoolName,
            logoImageUrl = logoImageUrl
        )

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class SearchSchoolTwo(val secondSearchSchoolData: SecondSearchSchoolData) : Event()
        data class ErrorMessage(val message: String) : Event()
    }
}