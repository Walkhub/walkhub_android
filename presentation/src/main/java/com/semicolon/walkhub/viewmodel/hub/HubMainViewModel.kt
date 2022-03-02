package com.semicolon.walkhub.viewmodel.hub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.rank.SchoolRankEntity
import com.semicolon.domain.enum.DateType
import com.semicolon.domain.exception.basic.NoInternetException
import com.semicolon.domain.usecase.rank.FetchSchoolRankUseCase
import com.semicolon.domain.usecase.socket.CheeringUseCase
import com.semicolon.domain.usecase.socket.ConnectedSocketUseCase
import com.semicolon.walkhub.ui.hub.model.HubSchoolRankData
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HubMainViewModel @Inject constructor(
    private val connectedSocketUseCase: ConnectedSocketUseCase,
    private val cheeringUseCase: CheeringUseCase,
    private val fetchSchoolRankUseCase: FetchSchoolRankUseCase

) : ViewModel() {

    init {
        connectedSocket()
        cheeringUseCase()
    }

    private fun connectedSocket() {
        viewModelScope.launch {
            connectedSocketUseCase.execute(Unit)
        }
    }

    private fun cheeringUseCase() {
        viewModelScope.launch {
            delay(3000)
            try {
                cheeringUseCase.execute(5).collect {
                    println("aaa $it")
                }
            }catch (e: Throwable){
                println("ee $e")
            }
        }
    }

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
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
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