package com.semicolon.walkhub.viewmodel.hub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.rank.OurSchoolUserRankEntity
import com.semicolon.domain.entity.rank.UserRankEntity
import com.semicolon.domain.enums.MoreDateType
import com.semicolon.domain.enums.RankScope
import com.semicolon.domain.exception.BadRequestException
import com.semicolon.domain.exception.NoInternetException
import com.semicolon.domain.exception.NotFoundException
import com.semicolon.domain.param.rank.FetchOurSchoolUserRankParam
import com.semicolon.domain.param.rank.FetchUserRankParam
import com.semicolon.domain.usecase.exercise.FetchExercisingUserListUseCase
import com.semicolon.domain.usecase.rank.FetchOurSchoolUserRankUseCase
import com.semicolon.domain.usecase.rank.FetchUserRankUseCase
import com.semicolon.domain.usecase.socket.CheeringUseCase
import com.semicolon.walkhub.BR
import com.semicolon.walkhub.R
import com.semicolon.walkhub.adapter.RecyclerViewItem
import com.semicolon.walkhub.ui.cheering.CheeringItemViewModel
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HubUserViewModel @Inject constructor(
    private val fetchOurSchoolUserRankUseCase: FetchOurSchoolUserRankUseCase,
    private val fetchUserRankUseCase: FetchUserRankUseCase,
    private val fetchExercisingUserListUseCase: FetchExercisingUserListUseCase,
    private val cheeringUseCase: CheeringUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private val _recyclerViewItem = MutableLiveData<List<RecyclerViewItem>>()
    val recyclerViewItem: LiveData<List<RecyclerViewItem>> = _recyclerViewItem

    private val _myRanking = MutableLiveData<HubMyPageItem>()
    val myRanking: LiveData<HubMyPageItem> = _myRanking

    private val _isJoinedClass = MutableLiveData<Boolean>()
    val isJoinedClass: LiveData<Boolean> = _isJoinedClass

    fun fetchMySchoolUserRank(scope: RankScope, dateType: MoreDateType) {

        viewModelScope.launch {
            kotlin.runCatching {
                val fetchMySchoolUserRank = fetchOurSchoolUserRankUseCase.execute(
                    FetchOurSchoolUserRankParam(
                        scope,
                        dateType
                    )
                )
                val fetchExercisingUserList = fetchExercisingUserListUseCase.execute(Unit)

                fetchMySchoolUserRank.zip(fetchExercisingUserList) { rankingList, exercisingList ->
                    HubMySchoolList(
                        userRank = rankingList,
                        exercisingUserIdList = exercisingList.map { it.userId })
                }.collect {
                    _recyclerViewItem.value = ArrayList<RecyclerViewItem>().apply {
                        it.userRank.rankingList.forEach { data ->
                            add(
                                if (it.exercisingUserIdList.contains(data.userId)) {
                                    data.toCheeringRecyclerviewItem()
                                } else {
                                    data.toRecyclerviewItem()
                                }
                            )
                        }
                    }

                    val myRank: Int = it.userRank.myRanking?.ranking ?: 1

                    if (it.userRank.myRanking != null) {
                        val topWalkCount =
                            if (myRank <= 1) 0
                            else it.userRank.rankingList[myRank - 2].walkCount
                        val downWalkCount =
                            if (myRank >= it.userRank.rankingList.size) 0
                            else it.userRank.rankingList[myRank].walkCount
                        val myWalkCount = it.userRank.myRanking!!.walkCount

                        _myRanking.value = HubMyPageItem(
                            topWalkCount,
                            myWalkCount,
                            downWalkCount,
                            it.userRank.myRanking!!.toData()
                        )
                    }

                    _isJoinedClass.value = it.userRank.isJoinedClass
                }
            }.onFailure {
                when (it) {
                    is NoInternetException -> errorToastMessage("인터넷을 사용할 수 없습니다")
                    is BadRequestException -> errorToastMessage("요청하는 형식을 식별할 수 없습니다.")
                    is NotFoundException -> errorToastMessage("요청하는 대상을 찾을 수 없습니다.")
                    else -> errorToastMessage("알 수 없는 에러가 발생했습니다.")
                }
            }
        }
    }

    data class HubMyPageItem(
        val topWalkCount: Int,
        val myWalkCount: Int,
        val downWalkCount: Int,
        val myPageData: HubMyPageData
    )

    data class HubMySchoolList(
        val userRank: OurSchoolUserRankEntity,
        val exercisingUserIdList: List<Int>
    )

    fun fetchSchoolUserRank(school: Int, dateType: MoreDateType) {
        viewModelScope.launch {
            kotlin.runCatching {
                val fetchSchoolUserRank =
                    fetchUserRankUseCase.execute(FetchUserRankParam(school, dateType))

                fetchSchoolUserRank.collect {
                    _recyclerViewItem.value = ArrayList<RecyclerViewItem>().apply {
                        addAll(
                            it.rankList.toUserRecyclerviewItem()
                        )
                    }
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

    private fun List<UserRankEntity.UserRank>.toUserRecyclerviewItem(): List<RecyclerViewItem> =
        map {
            RecyclerViewItem(
                itemLayoutId = R.layout.hub_user_search_view,
                variableId = BR.vm,
                data = it.toRecyclerview()
            )
        }

    private fun UserRankEntity.UserRank.toRecyclerview() =
        UserRankItemViewModel(
            profileUrl = profileImageUrl,
            name = name,
            walkCount = walkCount,
            rank = ranking
        )

    private fun OurSchoolUserRankEntity.Ranking.toRecyclerviewItem(): RecyclerViewItem =
        RecyclerViewItem(
            itemLayoutId = R.layout.hub_user_search_view,
            variableId = BR.vm,
            data = this.toItemViewModel()
        )

    private fun OurSchoolUserRankEntity.Ranking.toCheeringRecyclerviewItem() =
        RecyclerViewItem(
            itemLayoutId = R.layout.item_cheering,
            variableId = BR.vm,
            data = this.toCheeringItemViewModel()
        )


    private fun OurSchoolUserRankEntity.Ranking.toItemViewModel() =
        UserRankItemViewModel(
            profileUrl = profileImageUrl,
            name = name,
            walkCount = walkCount,
            rank = ranking
        )

    private fun OurSchoolUserRankEntity.Ranking.toCheeringItemViewModel() =
        HubCheeringItemViewModel(
            id = userId,
            userName = name,
            imageUrl = profileImageUrl
        )

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    private fun errorToastMessage(message: String) {
        event(Event.ErrorMessage(message))
    }

    inner class HubCheeringItemViewModel(id: Int, userName: String, imageUrl: String) :
        CheeringItemViewModel(id, userName, imageUrl) {

        override fun onClick() {
            viewModelScope.launch {
                cheeringUseCase.execute(id)
            }
        }
    }

    data class UserRankItemViewModel(
        val profileUrl: String,
        val name: String,
        val walkCount: Int,
        val rank: Int
    )

    data class HubMyPageData(
        val name: String,
        val profileImageUrl: String,
        val ranking: Int,
        val userId: Int,
        val walkCount: Int
    )

    fun OurSchoolUserRankEntity.Ranking.toData() =
        HubMyPageData(
            name = name,
            profileImageUrl = profileImageUrl,
            ranking = ranking,
            userId = userId,
            walkCount = walkCount
        )

    sealed class Event {
        data class ErrorMessage(val message: String) : Event()
    }
}