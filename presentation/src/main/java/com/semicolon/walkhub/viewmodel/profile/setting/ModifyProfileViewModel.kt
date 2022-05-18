package com.semicolon.walkhub.viewmodel.profile.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.users.FetchInfoEntity
import com.semicolon.domain.exception.*
import com.semicolon.domain.param.user.UpdateProfileParam
import com.semicolon.domain.usecase.user.DeleteClassUseCase
import com.semicolon.domain.usecase.user.FetchInfoUseCase
import com.semicolon.domain.usecase.user.UpdateProfileUseCase
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModifyProfileViewModel @Inject constructor(
    private val fetchInfoUseCase: FetchInfoUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val deleteClassUseCase: DeleteClassUseCase,
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun fetchInfo() {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchInfoUseCase.execute(Unit).collect {
                    event(Event.FetchInfo(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷에 연결되어있지 않습니다."))
                    is UnauthorizedException -> event(Event.ErrorMessage("세션이 만료되었습니다. 다시 시도해주세요."))
                    else -> event(Event.ErrorMessage("에러가 발생했습니다."))
                }
            }
        }
    }

    fun updateProfile(name: String, profileImage: String, schoolId: Long) {
        viewModelScope.launch {
            kotlin.runCatching {
                updateProfileUseCase.execute(UpdateProfileParam(
                    name = name,
                    profileImage = profileImage,
                    schoolId = schoolId))
            }.onFailure {
                when (it) {
                    is UnauthorizedException -> event(Event.ErrorMessage("세션이 만료되었습니다. 다시 시도해주세요."))
                    is BadRequestException -> event(Event.ErrorMessage("요청 형식을 식별할 수 없습니다."))
                    is NoInternetException -> event(Event.ErrorMessage("인터넷에 연결되어있지 않습니다."))
                    is KotlinNullPointerException -> event(Event.Success)
                    else -> event(Event.ErrorMessage("에러가 발생했습니다."))
                }
            }
        }
    }

    fun deleteClass() {
        viewModelScope.launch {
            kotlin.runCatching {
                deleteClassUseCase.execute(Unit)
            }.onFailure {
                when (it) {
                    is NotFoundException -> event(Event.ErrorMessage("요청하는 대상을 확인할 수 없습니다."))
                    is UnauthorizedException -> event(Event.ErrorMessage("세션이 만료되었습니다. 다시 시도해주세요."))
                    is NoInternetException -> event(Event.ErrorMessage("인터넷에 연결할 수 없습니다."))
                    else -> event(Event.ErrorMessage("에러가 발생했습니다."))
                }
            }
        }
    }

    private fun FetchInfoEntity.toData() =
        FetchInfoEntity(
            name = name,
            profileImageUrl = profileImageUrl,
            schoolName = schoolName,
            grade = grade,
            classNum = classNum
        )


    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        object Success: Event()
        data class FetchInfo(val fetchInfoData: FetchInfoEntity) : Event()
        data class ErrorMessage(val message: String) : Event()
    }
}