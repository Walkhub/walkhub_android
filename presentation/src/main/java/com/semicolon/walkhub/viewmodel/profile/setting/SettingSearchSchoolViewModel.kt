package com.semicolon.walkhub.viewmodel.profile.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.rank.SearchSchoolEntity
import com.semicolon.domain.exception.NoInternetException
import com.semicolon.domain.exception.NotFoundException
import com.semicolon.domain.usecase.rank.SearchSchoolUseCase
import com.semicolon.walkhub.ui.profile.setting.ui.model.ThirdSearchSchoolData
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingSearchSchoolViewModel @Inject constructor(
    private val searchSchoolUseCase: SearchSchoolUseCase
): ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun searchSchool(school: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                searchSchoolUseCase.execute(school).collect() {
                    event(Event.SearchSchoolThird(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷 연결을 확인해주세요."))
                    is NotFoundException -> event(Event.ErrorMessage("해당 학교가 존재하지 않습니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    private fun SearchSchoolEntity.toData() =
        ThirdSearchSchoolData(
            schoolList.map { it.toData() }
        )

    fun SearchSchoolEntity.SchoolInfo.toData() =
        ThirdSearchSchoolData.SchoolInfo(
            schoolId = schoolId,
            schoolName = schoolName,
            logoImageUrl = logoImageUrl
        )

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class SearchSchoolThird(val thirdSearchData: ThirdSearchSchoolData) : Event()
        data class ErrorMessage(val message: String) : Event()
    }

}