package com.semicolon.walkhub.viewmodel.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.messaging.FirebaseMessaging
import com.semicolon.data.remote.api.UserApi
import com.semicolon.domain.enums.SexType
import com.semicolon.domain.exception.*
import com.semicolon.domain.param.user.CheckPhoneNumberParam
import com.semicolon.domain.param.user.PostUserSignUpParam
import com.semicolon.domain.param.user.VerifyPhoneNumberSignUpParam
import com.semicolon.domain.usecase.user.CheckAccountOverlapUseCase
import com.semicolon.domain.usecase.user.CheckPhoneNumberUseCase
import com.semicolon.domain.usecase.user.PostUserSignUpUseCase
import com.semicolon.domain.usecase.user.VerifyUserPhoneNumberUseCase
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.NullPointerException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.properties.Delegates

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val checkAccountOverlapUseCase: CheckAccountOverlapUseCase,
    private val verifyUserPhoneNumberUseCase: VerifyUserPhoneNumberUseCase,
    private val checkPhoneNumberUseCase: CheckPhoneNumberUseCase,
    private val postUserSignUpUseCase: PostUserSignUpUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    companion object{
        var userId: String = ""
        var password: String = ""
        var name: String = ""
        var phone: String = ""
        var authCode: String = ""
        var height: Double = 0.0
        var weight: Int = 0
        var sex: SexType = SexType.X
        var schoolId: Int = 0
    }

    fun checkId(id: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                checkAccountOverlapUseCase.execute(id)
            }.onSuccess {
                event(Event.SuccessId(true))
            }.onFailure {
                when (it) {
                    is NoInternetException -> event(Event.ErrorMessage("인터넷 연결을 확인해주세요."))
                    is ConflictException -> event(Event.ErrorMessage("이미 존재하는 아이디입니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 오류가 발생하였습니다."))
                }
            }
        }
    }

    fun verifyPhone(phone_number: VerifyPhoneNumberSignUpParam) {
        viewModelScope.launch {
            kotlin.runCatching {
                verifyUserPhoneNumberUseCase.execute(phone_number)
            }.onSuccess {
                event(Event.SuccessVerityPhone(true))
            }.onFailure {
                when (it) {
                    is UnauthorizedException -> event(Event.ErrorMessage("토큰이 만료되었습니다. 재로그인 해주세요."))
                    is NotFoundException -> event(Event.ErrorMessage("잘못된 접근입니다.."))
                    is ConflictException -> event(Event.ErrorMessage("이미 회원가입 완료된 전화번호입니다."))
                    else -> event(Event.ErrorMessage("알 수 없는 오류가 발생하였습니다."))
                }
            }
        }
    }

    fun checkPhoneNumber(checkPhoneNumberParam: CheckPhoneNumberParam) {
        viewModelScope.launch {
            kotlin.runCatching {
                checkPhoneNumberUseCase.execute(checkPhoneNumberParam)
            }.onSuccess {
                event(Event.SuccessCheckPhone(true))
            }.onFailure {
                when (it) {
                    is UnauthorizedException -> event(Event.ErrorMessage("인증번호가 올바르지 않습니다."))
                    is NotFoundException -> event(Event.ErrorMessage("인증번호가 올바르지 않습니다."))
                    is NullPointerException -> event(Event.ErrorMessage("Null"))
                    else -> event(Event.ErrorMessage("이미 회원가입 완료된 전화번호입니다."))
                }
            }
        }
    }

    fun register() {
        viewModelScope.launch {
            kotlin.runCatching {
                postUserSignUpUseCase.execute(PostUserSignUpParam(
                    accountId = userId,
                    password = password,
                    name = name,
                    phoneNumber = phone,
                    height = height,
                    weight = weight,
                    sex = sex,
                    schoolId = schoolId,
                    authCode = authCode
                ))
            }.onSuccess {
                event((Event.SuccessRegister(true)))
            }.onFailure {
                when (it) {
                    is BadRequestException -> event(Event.BadRequest)
                    is NotFoundException -> event(Event.NotFound)
                    is UnauthorizedException -> event(Event.UnAuthor)
                    is ConflictException -> event(Event.Conflict)
                    else -> event(Event.UnKnown)
                }
            }
        }
    }

    fun setBody(Height: Double?, Weight: Int?) {
        if (Height != null) {
            height = Height
        }
        if (Weight != null) {
            weight = Weight
        }
    }

    fun setSchool(SchoolId: Int) {
        schoolId = SchoolId
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class SuccessRegister(var state: Boolean = false) : Event()
        data class SuccessCheckPhone(var state1: Boolean = false) : Event()
        data class SuccessId(var state2: Boolean = false) : Event()
        data class SuccessVerityPhone(var state3: Boolean = false) : Event()
        object BadRequest: Event()
        object NotFound: Event()
        object UnAuthor: Event()
        object Conflict: Event()
        object UnKnown: Event()
        data class ErrorMessage(val message: String) : Event()
    }
}