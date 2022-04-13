package com.semicolon.walkhub.viewmodel.hub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.enums.MoreDateType
import com.semicolon.domain.enums.RankScope
import com.semicolon.domain.exception.BadRequestException
import com.semicolon.domain.exception.NoInternetException
import com.semicolon.domain.exception.NotFoundException
import com.semicolon.domain.param.rank.FetchOurSchoolUserRankParam
import com.semicolon.domain.param.rank.FetchUserRankParam
import com.semicolon.domain.usecase.rank.FetchOurSchoolUserRankUseCase
import com.semicolon.domain.usecase.rank.FetchUserRankUseCase
import com.semicolon.domain.usecase.socket.CheeringUseCase
import com.semicolon.walkhub.BR
import com.semicolon.walkhub.R
import com.semicolon.walkhub.adapter.RecyclerViewItem
import com.semicolon.walkhub.ui.cheering.CheeringItemViewModel
import com.semicolon.walkhub.ui.hub.model.MySchoolUserRankData
import com.semicolon.walkhub.ui.hub.model.SchoolUserRankData
import com.semicolon.walkhub.ui.hub.model.toData
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HubUserViewModel @Inject constructor(
    private val fetchOurSchoolUserRankUseCase: FetchOurSchoolUserRankUseCase,
    private val fetchUserRankUseCase: FetchUserRankUseCase,
    private val cheeringUseCase: CheeringUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private val _recyclerViewItems = MutableLiveData<List<RecyclerViewItem>>()
    val recyclerViewItem: LiveData<List<RecyclerViewItem>> = _recyclerViewItems

    fun fetchMySchoolUserRank(scope: RankScope, dateType: MoreDateType) {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchOurSchoolUserRankUseCase.execute(FetchOurSchoolUserRankParam(scope, dateType))
                    .collect {
                        event(Event.FetchMySchoolUserRank(it.toData()))
                    }
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷을 사용할 수 없습니다"))
                    is BadRequestException -> event(Event.ErrorMessage("요청하는 형식을 식별할 수 없습니다."))
                    is NotFoundException -> event(Event.ErrorMessage("요청하는 대상을 찾을 수 없습니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    fun test() {
        _recyclerViewItems.value = ArrayList<RecyclerViewItem>().apply {
            add(RecyclerViewItem(itemLayoutId = R.layout.item_challenge, variableId = BR.vm, data = 123))
            add(RecyclerViewItem(itemLayoutId = R.layout.item_cheering, variableId = BR.vm, data = 123))
        }
    }

    fun fetchSchoolUserRank(school: Int, dateType: MoreDateType) {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchUserRankUseCase.execute(FetchUserRankParam(school, dateType)).collect {
                    event(Event.FetchOtherSchoolUserRank(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷을 사용할 수 없습니다"))
                    is BadRequestException -> event(Event.ErrorMessage("요청하는 형식을 식별할 수 없습니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    fun fetchExercisingUser() {
        _recyclerViewItems.value = ArrayList<RecyclerViewItem>().apply {
            add(
                RecyclerViewItem(
                    itemLayoutId = R.layout.item_cheering,
                    variableId = BR.vm,
                    data = HubCheeringItemViewModel(12, "user", "https//")
                )
            )
        }
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    inner class HubCheeringItemViewModel(id: Int, userName: String, imageUrl: String) :
        CheeringItemViewModel(id, userName, imageUrl) {

        override fun onClick() {
            viewModelScope.launch {
                cheeringUseCase.execute(id)
            }
        }
    }

    sealed class Event {
        data class FetchMySchoolUserRank(val mySchoolUserRankData: MySchoolUserRankData) : Event()
        data class FetchOtherSchoolUserRank(val userRankData: SchoolUserRankData) : Event()
        data class ErrorMessage(val message: String) : Event()
    }
}