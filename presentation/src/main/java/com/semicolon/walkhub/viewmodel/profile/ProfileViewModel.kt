package com.semicolon.walkhub.viewmodel.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.users.UserMyPageEntity
import com.semicolon.domain.exception.NoInternetException
import com.semicolon.domain.exception.NotFoundException
import com.semicolon.domain.exception.UnauthorizedException
import com.semicolon.domain.usecase.user.FetchMypageUseCase
import com.semicolon.walkhub.ui.profile.model.MyPageData
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val fetchMypageUseCase: FetchMypageUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()


    fun fetchMyPage() {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchMypageUseCase.execute(Unit)
                    .collect {
                        event(Event.FetchMyPage(it.toData()))
                    }
            }.onFailure {
                when (it) {
                    is UnauthorizedException -> event(Event.ErrorMessage("토큰이 만료되었거나 식별할 수 없습니다."))
                    is NotFoundException -> event(Event.ErrorMessage("요청하는 대상을 찾을 수 없습니다."))
                    is NoInternetException -> event(Event.ErrorMessage("인터넷에 연결되지 않았습니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    private fun UserMyPageEntity.toData() =
        MyPageData(
            userId = userId,
            name = name,
            profileImageUrl = profileImageUrl,
            schoolName = schoolName,
            schoolImageUrl = schoolImageUrl,
            grade = grade,
            classNum = classNum,
            dailyWalkCountGoal = dailyWalkCountGoal,
            titleBadge = titleBadge.toData(),
            level = level.toData()
        )

    fun UserMyPageEntity.TitleBadge.toData() =
        MyPageData.TitleBadge(
            badgeId = badgeId,
            badgeName = badgeName,
            badgeImageUrl = badgeImageUrl
        )

    fun UserMyPageEntity.Level.toData() =
        MyPageData.Level(
            levelId = levelId,
            levelName = levelName,
            levelImageUrl = levelImageUrl
        )


    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class FetchMyPage(val rankData: MyPageData): Event()
        data class ErrorMessage(val message: String) : Event()
    }
}