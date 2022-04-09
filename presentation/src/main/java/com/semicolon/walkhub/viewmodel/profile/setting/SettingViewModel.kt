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
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import com.semicolon.walkhub.viewmodel.home.HomeViewModel
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

    fun fetchUserHealth() {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchUserHealthUseCase.execute(Unit).collect {
                        event(Event.FetchUserHealth(it.toData()))
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

    fun fetchAuthInfo() {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchAuthInfoUseCase.execute(Unit).collect {
                        event(Event.FetchAuthInfo(it.toData()))
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

    fun patchUserHealth(height: Double, weight: Int, sex: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                patchUserHealthUseCase.execute(PatchUserHealthParam(height, weight, sex))
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷에 연결되어있지 않습니다."))
                    is BadRequestException -> event(Event.ErrorMessage("요청이 잘못되었습니다. 입력 값을 확인해주세요."))
                    is UnauthorizedException -> event(Event.ErrorMessage("세션이 만료되었습니다. 다시 시도해주세요."))
                    else -> event(Event.ErrorMessage("에러가 발생했습니다."))
                }
            }
        }
    }

    fun patchUserChangePassword(
        accountId: String, phoneNumber: String, authcode: String, newPassword: String
    ) {
        viewModelScope.launch {
            kotlin.runCatching {
                patchUserChangePasswordUseCase.execute(
                    PatchUserChangePasswordParam(
                        accountId, phoneNumber, authcode, newPassword
                    )
                )
            }.onFailure {
                when (it) {
                    is BadRequestException -> event(Event.ErrorMessage("요청이 잘못되었습니다. 입력 값을 확인해주세요."))
                    is UnauthorizedException -> event(Event.ErrorMessage("세션이 만료되었습니다. 다시 시도해주세요."))
                    is NotFoundException -> event(Event.ErrorMessage("요청하는 대상을 확인할 수 없습니다."))
                    is NoInternetException -> event(Event.ErrorMessage("인터넷에 연결되어있지 않습니다."))
                    else -> event(Event.ErrorMessage("에러가 발생했습니다."))
                }
            }
        }
    }

    fun updateProfile(name: String, profileImage: File?, schoolId: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                updateProfileUseCase.execute(UpdateProfileParam(name, profileImage, schoolId))
            }.onFailure {
                when (it) {
                    is UnauthorizedException -> event(Event.ErrorMessage("세션이 만료되었습니다. 다시 시도해주세요."))
                    is BadRequestException -> event(Event.ErrorMessage("요청 형식을 식별할 수 없습니다."))
                    is NoInternetException -> event(Event.ErrorMessage("인터넷에 연결되어있지 않습니다."))
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

    fun deleteAccount() {
        viewModelScope.launch {
            kotlin.runCatching {
                deleteAccountUseCase.execute(Unit)
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

    private fun FetchUserHealthEntity.toData() =
        FetchUserHealthEntity(
            height = height,
            weight = weight,
            sex = sex
        )

    private fun FetchAuthInfoEntity.toData() =
        FetchAuthInfoEntity(
            accountId = accountId,
            phoneNumber = phoneNumber
        )

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
        data class FetchInfo(val fetchInfoData: FetchInfoEntity) : Event()
        data class FetchUserHealth(val fetchUserHealthData: FetchUserHealthEntity) : Event()
        data class FetchAuthInfo(val fetchAuthInfoData: FetchAuthInfoEntity) : Event()
        data class ErrorMessage(val message: String) : SettingViewModel.Event()
    }
}/