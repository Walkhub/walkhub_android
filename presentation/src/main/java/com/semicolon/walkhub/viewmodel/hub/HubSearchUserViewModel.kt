package com.semicolon.walkhub.viewmodel.hub

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.rank.SearchUserEntity
import com.semicolon.domain.enum.MoreDateType
import com.semicolon.domain.exception.basic.NoInternetException
import com.semicolon.domain.exception.basic.NotFoundException
import com.semicolon.domain.param.rank.SearchUserParam
import com.semicolon.domain.usecase.rank.SearchUserUseCase
import com.semicolon.walkhub.ui.hub.model.SearchUserData
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HubSearchUserViewModel @Inject constructor(
    private val searchUserUseCase: SearchUserUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private var searchJob: Job? = null

    fun searchUser(school: Int, name: String, dateType: MoreDateType) {
        viewModelScope.launch {
            kotlin.runCatching {
                searchUserUseCase.execute(SearchUserParam(school, name, dateType)).collect() {
                    event(Event.SearchSchool(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷을 사용할 수 없습니다"))
                    is NotFoundException -> event(Event.ErrorMessage("요청하는 대상을 찾을 수 없습니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다. ${it}"))
                }
            }
        }
    }

    fun searchUserDebounced(school: Int, name: String, dateType: MoreDateType) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            searchUser(school, name, dateType)
        }
    }

    fun SearchUserEntity.toData() =
        SearchUserData(
            userList.map { it.toData() }
        )

    fun SearchUserEntity.UserInfo.toData() =
        SearchUserData.UserInfo(
            profileUrl = profileImageUrl,
            rank = ranking,
            name = name,
            walkCount = walkCount
        )

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class SearchSchool(val userData: SearchUserData) : Event()
        data class ErrorMessage(val message: String) : Event()
    }
}