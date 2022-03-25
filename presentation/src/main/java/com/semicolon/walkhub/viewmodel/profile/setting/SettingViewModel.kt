package com.semicolon.walkhub.viewmodel.profile.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.users.FetchAuthInfoEntity
import com.semicolon.domain.entity.users.FetchInfoEntity
import com.semicolon.domain.entity.users.FetchUserHealthEntity
import com.semicolon.domain.exception.BadRequestException
import com.semicolon.domain.exception.NoInternetException
import com.semicolon.domain.exception.NotFoundException
import com.semicolon.domain.exception.UnauthorizedException
import com.semicolon.domain.param.user.PatchUserChangePasswordParam
import com.semicolon.domain.param.user.PatchUserHealthParam
import com.semicolon.domain.param.user.UpdateProfileParam
import com.semicolon.domain.usecase.user.*
import com.semicolon.walkhub.ui.profile.setting.model.*
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val patchUserHealthUseCase: PatchUserHealthUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val patchUserChangePasswordUseCase: PatchUserChangePasswordUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase,
    private val fetchInfoUseCase: FetchInfoUseCase,
    private val fetchUserHealthUseCase: FetchUserHealthUseCase,
    private val fetchAuthInfoUseCase: FetchAuthInfoUseCase,
    private val deleteClassUseCase: DeleteClassUseCase


) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private fun fetchUserHealth() {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchUserHealthUseCase.execute(Unit).collect() {
                    event(Event.FetchUserHealth(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷을 사용할 수 없습니다"))
                    is UnauthorizedException -> event(Event.ErrorMessage("토큰이 만료되었거나 식별 할 수 없습니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    private fun fetchAuthInfo() {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchAuthInfoUseCase.execute(Unit).collect() {
                    event(Event.FetchAuthInfo(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷을 사용할 수 없습니다"))
                    is UnauthorizedException -> event(Event.ErrorMessage("토큰이 만료되었거나 식별 할 수 없습니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    private fun fetchInfo() {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchInfoUseCase.execute(Unit).collect() {
                    event(Event.FetchInfo(it.toData()))
                }
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷을 사용할 수 없습니다"))
                    is UnauthorizedException -> event(Event.ErrorMessage("토큰이 만료되었거나 식별 할 수 없습니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    private fun patchUserHealth(height: Double, weight: Int, sex: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                patchUserHealthUseCase.execute(PatchUserHealthParam(height, weight, sex))
            }.onSuccess {
                event(Event.Success(""))
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷을 사용할 수 없습니다"))
                    is BadRequestException -> event(Event.ErrorMessage("요청 형식을 식별할 수 없습니다."))
                    is UnauthorizedException -> event(Event.ErrorMessage("토큰이 만료되었거나 식별 할 수 없습니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    private fun patchUserChangePassword(
        accountId: String, phoneNumber: String, authcode: String, newPassword: String
    ) {
        viewModelScope.launch {
            kotlin.runCatching {
                patchUserChangePasswordUseCase.execute(
                    PatchUserChangePasswordParam(
                        accountId, phoneNumber, authcode, newPassword
                    )
                )
            }.onSuccess {
                event(Event.Success(""))
            }.onFailure {
                when (it) {
                    is BadRequestException -> event(Event.ErrorMessage("요청 형식을 식별할 수 없습니다."))
                    is UnauthorizedException -> event(Event.ErrorMessage("토큰이 만료되었거나 식별 할 수 없습니다."))
                    is NotFoundException -> event(Event.ErrorMessage("요청하는 대상을 확인할 수 없습니다."))
                    is NoInternetException -> event(Event.ErrorMessage("인터넷에 연결할 수 없습니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    private fun updateProfile(name: String, profileImage: File?, schoolId: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                updateProfileUseCase.execute(UpdateProfileParam(name, profileImage, schoolId))
            }.onSuccess {
                event(Event.Success(""))
            }.onFailure {
                when (it) {
                    is UnauthorizedException -> event(Event.ErrorMessage("토큰이 만료되었거나 식별할 수 없습니다."))
                    is BadRequestException -> event(Event.ErrorMessage("요청 형식을 식별할 수 없습니다."))
                    is NoInternetException -> event(Event.ErrorMessage("인터넷에 연결할 수 없습니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    private fun deleteClass() {
        viewModelScope.launch {
            kotlin.runCatching {
                deleteClassUseCase.execute(Unit)
            }.onSuccess {
                event(Event.Success(""))
            }.onFailure {
                when (it) {
                    is NotFoundException -> event(Event.ErrorMessage("요청 대상을 찾을 수 없습니다."))
                    is UnauthorizedException -> event(Event.ErrorMessage("토큰이 만료되었거나 식별할 수 없습니다."))
                    is NoInternetException -> event(Event.ErrorMessage("인터넷에 연결할 수 없습니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }

    private fun deleteAccount() {
        viewModelScope.launch {
            kotlin.runCatching {
                deleteAccountUseCase.execute(Unit)
            }.onSuccess {
                event(Event.Success(""))
            }.onFailure {
                when (it) {
                    is NotFoundException -> event(Event.ErrorMessage("요청 대상을 찾을 수 없습니다."))
                    is UnauthorizedException -> event(Event.ErrorMessage("토큰이 만료되었거나 식별할 수 없습니다."))
                    is NoInternetException -> event(Event.ErrorMessage("인터넷에 연결할 수 없습니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 에러가 발생했습니다."))
                }
            }
        }
    }


    private fun FetchUserHealthEntity.toData() =
        FetchUserHealthData(
            height = height,
            weight = weight,
            sex = sex
        )

    private fun FetchAuthInfoEntity.toData() =
        FetchAuthInfoData(
            accountId = accountId,
            phoneNumber = phoneNumber
        )

    private fun FetchInfoEntity.toData() =
        FetchInfoData(
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
        data class PatchUserChagnePassword(val patchUserChangePasswordData: PatchUserChangePasswordData) : Event()
        data class FetchInfo(val fetchInfoData: FetchInfoData) : Event()
        data class FetchUserHealth(val fetchUserHealthData: FetchUserHealthData) : Event()
        data class UpdateProfile(val updateProfileData: UpdateProfileData) : Event()
        data class FetchAuthInfo(val fetchAuthInfoData: FetchAuthInfoData) : Event()
        data class PatchUserHealth(val patchUserHealthData: PatchUserHealthData) : Event()
        data class Success(val message: String) : Event()
        data class ErrorMessage(val message: String) : SettingViewModel.Event()
    }
}