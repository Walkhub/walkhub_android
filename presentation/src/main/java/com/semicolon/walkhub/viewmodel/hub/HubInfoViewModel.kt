package com.semicolon.walkhub.viewmodel.hub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.notice.NoticeEntity
import com.semicolon.domain.entity.school.SchoolDetailEntity
import com.semicolon.domain.enums.NoticeType
import com.semicolon.domain.exception.NoInternetException
import com.semicolon.domain.param.notice.FetchNoticeListParam
import com.semicolon.domain.usecase.notice.FetchNoticeListUseCase
import com.semicolon.domain.usecase.school.FetchSchoolDetailUseCase
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HubInfoViewModel @Inject constructor(
    private val fetchSchoolDetailUseCase: FetchSchoolDetailUseCase,
    private val fetchNoticeListUseCase: FetchNoticeListUseCase
): ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun fetchSchoolDetail(schoolId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchSchoolDetailUseCase.execute(schoolId).collect {
                    event(Event.FetchSchoolDetail(it))
                }
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷을 사용할 수 없습니다"))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    fun fetchNoticeList(noticeType: NoticeType) {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchNoticeListUseCase.execute(FetchNoticeListParam(noticeType.toString())).collect {
                    event(Event.FetchNoticeList(it))
                }
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷을 사용할 수 없습니다"))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class ErrorMessage(val message: String): Event()
        data class FetchSchoolDetail(val schoolDetail: SchoolDetailEntity): Event()
        data class FetchNoticeList(val noticeList: NoticeEntity): Event()
    }

}