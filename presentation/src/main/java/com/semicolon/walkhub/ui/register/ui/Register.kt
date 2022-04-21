package com.semicolon.walkhub.ui.register.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityRegisterBinding
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.util.visible
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.isGone
import com.semicolon.domain.enums.SexType
import com.semicolon.domain.param.user.CheckPhoneNumberParam
import com.semicolon.domain.param.user.PostUserSignUpParam
import com.semicolon.domain.param.user.VerifyPhoneNumberSignUpParam
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.MainActivity
import com.semicolon.walkhub.ui.register.ScanHealthInformationActivity
import com.semicolon.walkhub.ui.register.SearchSchoolActivity
import com.semicolon.walkhub.util.invisible
import com.semicolon.walkhub.viewmodel.login.LoginViewModel
import com.semicolon.walkhub.viewmodel.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates
import android.R.string
import java.util.regex.Pattern


@AndroidEntryPoint
class Register : BaseActivity<ActivityRegisterBinding>(
    R.layout.activity_register
) {
    private val vm: RegisterViewModel by viewModels()

    var a: Int? = null
    var id: String = ""
    var phone: String = ""
    var schoolId: Int = 0
    var data: Int = 0
    var schoolname: String = ""
    var school: String = ""

    var temp = false

    var b: Boolean = false
    override fun initView() {

        temp = intent.getBooleanExtra("movePage", temp)
        if (temp) {
            movePage(6)
            searchSchool()
            data = intent.getIntExtra("data", schoolId)
            school = intent.getStringExtra("school").toString()
            binding.tvNull.text = school
            return
        }

        binding.constraint.setOnClickListener {
            hideKeyboard()
        }

        binding.btReCer.setOnClickListener {
            val verifyPhoneNumberSignUpParam = VerifyPhoneNumberSignUpParam(phone_number = phone)
            vm.verifyPhone(verifyPhoneNumberSignUpParam)
        }

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

        movePage(1)
    }

    private fun handleEvent(event: RegisterViewModel.Event) = when (event) {
        is RegisterViewModel.Event.SuccessVerityPhone -> {
            movePage(3)
        }

        is RegisterViewModel.Event.SuccessCheckPhone -> {
            movePage(4)
        }

        is RegisterViewModel.Event.SuccessId -> {
            movePage(5)
        }

        is RegisterViewModel.Event.SuccessRegister -> {
            TODO("여기 부분 구현")
        }

        is RegisterViewModel.Event.ErrorMessage -> showShortToast(event.message)

        is RegisterViewModel.Event.BadRequest -> showShortToast("비밀번호에는 특수문자가 포함되어야합니다.")

        is RegisterViewModel.Event.UnAuthor -> showShortToast("토큰 발급에 실패하였습니다.")

        is RegisterViewModel.Event.Conflict -> showShortToast("이미 회원가입이 완료된 전화번호입니다.")

        is RegisterViewModel.Event.UnKnown -> showShortToast("알 수 없는 오류가 발생하였습니다.")

        is RegisterViewModel.Event.NotFound -> showShortToast("잘못된 접근입니다.")
    }

    private fun setTextWatcher(subject: Int) {
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                when (subject) {
                    1 -> {
                        if (p0?.length!! < 2) {
                            binding.btContinue.background = ContextCompat.getDrawable(
                                applicationContext, R.drawable.registerbuttondesign
                            )

                            binding.tvWarning.visible()
                            binding.tvWarning.text = "이름은 최소 2자 이상이여야 합니다."
                        }

                        if (p0.length > 10) {
                            binding.btContinue.background = ContextCompat.getDrawable(
                                applicationContext, R.drawable.registerbuttondesign
                            )

                            binding.tvWarning.visible()
                            binding.tvWarning.text = "이름은 최대 10자 이하여야 합니다."
                        }

                        if (p0.length in 2..10) {
                            binding.btContinue.background = ContextCompat.getDrawable(
                                applicationContext, R.drawable.register_btn
                            )

                            binding.tvWarning.visibility = View.GONE
                        }
                    }

                    2 -> {
                        when {
                            p0?.length!! < 11 -> {
                                binding.btContinue.background =
                                    ContextCompat.getDrawable(
                                        applicationContext,
                                        R.drawable.registerbuttondesign
                                    )

                                binding.tvWarning.visible()
                                binding.tvWarning.text = ""
                            }

                            p0.length in 11..11 -> {
                                binding.btContinue.background = ContextCompat.getDrawable(
                                    applicationContext,
                                    R.drawable.register_btn
                                )

                                binding.tvWarning.visibility = View.GONE
                            }

                            p0.length > 11 -> {
                                binding.btContinue.background =
                                    ContextCompat.getDrawable(
                                        applicationContext,
                                        R.drawable.registerbuttondesign
                                    )

                                binding.tvWarning.visible()
                                binding.tvWarning.text = "올바른 전화번호 형식을 입력해주세요."
                            }
                        }
                    }

                    3 -> {
                        when {
                            p0?.length in 5..5 -> {
                                binding.btContinue.background =
                                    ContextCompat.getDrawable(
                                        applicationContext,
                                        R.drawable.register_btn
                                    )

                                binding.tvWarning.visible()
                                binding.tvWarning.setTextColor(Color.parseColor("#57B4F1"))
                            }

                            p0?.length!! < 6 -> {
                                binding.tvWarning.visibility = View.GONE
                            }

                            p0.length > 5 -> {
                                binding.tvWarning.visible()
                                binding.tvWarning.setTextColor(Color.parseColor("#F33636"))
                                binding.tvWarning.text = "인증번호는 5글자입니다."
                            }
                        }
                    }

                    4 -> {
                        if (p0?.length in 5..30) {
                            binding.btContinue.background = ContextCompat.getDrawable(
                                applicationContext,
                                R.drawable.register_btn
                            )

                            binding.tvWarning.text = ""
                        }
                    }

                    5 -> {
                        if (p0?.length in 8..30) {
                            binding.btContinue.background = ContextCompat.getDrawable(
                                applicationContext,
                                R.drawable.register_btn
                            )
                        } else binding.btContinue.background = ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.registerbuttondesign
                        )
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    private fun movePage(page: Int) {
        viewGone()
        when (page) {
            1 -> {
                enterName()

                binding.btContinue.setOnClickListener {
                    val name = binding.etName.text.toString()

                    if (name.length in 2..10) {
                        RegisterViewModel.name = name
                        movePage(2)
                    } else {
                        showShortToast("이름은 (2~10)자 안으로 입력해주세요.")
                    }
                }

                binding.ibBack.setOnClickListener {
                    finish()
                    enterName()
                }
            }

            2 -> {
                enterPhone()

                binding.btContinue.setOnClickListener {
                    phone = binding.etName.text.toString()

                    if (phone.length in 11..11) {
                        RegisterViewModel.phone = phone

                        verifyPhone(
                            verifyPhoneNumberSignUpParam = VerifyPhoneNumberSignUpParam(
                                phone
                            )
                        )
                    } else {
                        showShortToast("전화번호를 올바르게 입력해주세요.")
                    }
                }

                binding.ibBack.setOnClickListener {
                    movePage(1)
                }
            }
            3 -> {
                sendCertification()

                binding.btContinue.setOnClickListener {
                    val cer = binding.etName.text.toString()

                    when {
                        cer.length < 5 -> {
                            showShortToast("올바른 형식의 인증번호를 입력해주세요.")
                        }
                        cer.length > 5 -> {
                            showShortToast("인증번호를 올바르게 입력해주세요.")
                        }
                        else -> {
                            RegisterViewModel.authCode = cer
                            checkPhoneNumber(
                                checkPhoneNumberParam = CheckPhoneNumberParam(
                                    phone,
                                    cer
                                )
                            )
                        }
                    }
                }

                binding.ibBack.setOnClickListener {
                    movePage(2)
                }
            }
            4 -> {
                sendId()

                binding.btContinue.setOnClickListener {
                    val id = binding.etName.text.toString()

                    if (id.length < 4) {
                        showShortToast("5자 이상의 아이디를 입력해주세요.")
                    } else {
                        RegisterViewModel.userId = id
                        checkId()
                    }
                }

                binding.ibBack.setOnClickListener {
                    movePage(2)
                }
            }
            5 -> {
                sendPassWord()

                binding.btContinue.setOnClickListener {
                    val password = binding.etName.text.toString()

                    if (password.length in 1..7) {
                        showShortToast("8자 이상의 비밀번호를 입력해주세요.")
                    } else if (password.length > 7) {
                        val passwordPattern = "^.*(?=^.{8,30}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$"

                        val tt = Pattern.matches(passwordPattern, password)

                        if (tt){
                            RegisterViewModel.password = password
                            movePage(6)
                        } else {
                            showShortToast("비밀번호에는의 한개 이상 숫자와 특수문자가 포함되어야합니다.")
                        }
                    }
                }

                binding.ibBack.setOnClickListener {
                    movePage(4)
                }
            }

            6 -> {
                sendSchool()
                hideKeyboard()

                binding.etName.setOnClickListener {
                    val intent = Intent(this, SearchSchoolActivity::class.java)
                    startActivity(intent)
                }

                binding.btContinue.setOnClickListener {
                    if (binding.etName.length() > 0) {
                        vm.setSchool(data)
                    }
                    RegisterViewModel.schoolId = data
                    val intent = Intent(this, AgreeActivity::class.java)
                    startActivity(intent)
                }

                binding.ibBack.setOnClickListener {
                    movePage(5)
                }
            }
        }
    }

    private fun verifyPhone(verifyPhoneNumberSignUpParam: VerifyPhoneNumberSignUpParam) {
        id = binding.etName.text.toString()
        verifyPhoneNumberSignUpParam.phone_number = binding.etName.text.toString()
        vm.verifyPhone(verifyPhoneNumberSignUpParam)
    }

    private fun checkPhoneNumber(checkPhoneNumberParam: CheckPhoneNumberParam) {
        checkPhoneNumberParam.phoneNumber = phone
        checkPhoneNumberParam.authCode = binding.etName.text.toString()
        vm.checkPhoneNumber(checkPhoneNumberParam)
    }

    private fun checkId() {
        vm.checkId(binding.etName.text.toString())
    }

    private fun viewGone() {
        binding.run {
            etName.text = null
            tvWarning.visibility = View.GONE
            btContinue.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.registerbuttondesign
            )
        }
    }

    private fun enterName() {
        setTextWatcher(1)
        binding.tvMain.visible()
        binding.tvMain.text = "회원가입하고"
        binding.tvMain2.visible()
        binding.tvMain2.text = "친구들과 함께 걷기"
        binding.tvEt.text = "먼저 본인의 이름을 입력해주세요."
        binding.etName.hint = "이름(2~10자)"
        binding.etName.inputType = InputType.TYPE_CLASS_TEXT
    }

    private fun enterPhone() {
        setTextWatcher(2)

        binding.tvMain2.visibility = View.GONE
        binding.tvWarning.visibility = View.GONE
        binding.tvMinute.visibility = View.GONE
        binding.tvSecond.visibility = View.GONE
        binding.btReCer.visibility = View.GONE
        binding.tvMain.text = "전화번호 인증"
        binding.tvEt.text = "전화번호를 입력해주세요"
        binding.etName.hint = "전화번호 ex) 01012345678"
        binding.etName.inputType = InputType.TYPE_CLASS_NUMBER
    }

    private fun sendCertification() {
        setTextWatcher(3)

        binding.tvEt.visibility = View.GONE
        binding.tvWarning.visibility = View.GONE
        binding.etName.hint = "인증번호입력"
        binding.btReCer.visible()
        binding.tvMinute.visible()
        binding.tvSecond.visible()
        binding.devide.visible()
        binding.etName.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

        if (!b) fiveTimer()
    }

    private fun sendId() {
        setTextWatcher(4)

        binding.etName.hint = "아이디 (5~30자)"
        binding.tvMain.text = "아이디"
        binding.tvEt.visible()
        binding.tvEt.text = "아이디는 (5~30)자 포함"
        binding.devide.visibility = View.GONE
        binding.btReCer.visibility = View.GONE
        binding.tvMinute.visibility = View.GONE
        binding.tvSecond.visibility = View.GONE
        binding.etName.maxEms
    }

    private fun sendPassWord() {
        setTextWatcher(5)

        binding.tvMain.text = "비밀번호"
        binding.etName.hint = "비밀번호 (8~30자)"
        binding.tvEt.text = "비밀번호는 (8~30)자 및 숫자와 특수문자 \n각각 1개 이상 포함"
        binding.etName.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
    }

    private fun sendSchool() {
        binding.tvMain.text = "학교 등록"
        binding.tvEt.text = "현재 소속 중인 학교를 입력해주세요."
        binding.etName.hint = "학교 검색하기"
    }

    private fun searchSchool() {
        binding.tvNull.visible()
        binding.tvNull.text = schoolname
        binding.etName.hint = ""
        binding.etName.invisible()
    }

    private fun fiveTimer() {
        b = true
        object : CountDownTimer(300000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val num = (millisUntilFinished / 1000).toInt()
                binding.tvMinute.text = (num / 60).toString()
                binding.tvSecond.text = (num % 60).toString()

                when (num) {
                    0 -> {
                        a = binding.tvMinute.text.toString().toInt()
                        a!! - 1
                        binding.tvMinute.text = a.toString()
                        binding.tvSecond.text = 60.toString()
                    }
                }
            }

            override fun onFinish() {
                binding.tvMinute.text = "0"
                binding.tvSecond.text = "00"
                binding.devide.setTextColor((Color.parseColor("#F04D51")))
                binding.tvMinute.setTextColor((Color.parseColor("#F04D51")))
                binding.tvSecond.setTextColor((Color.parseColor("#F04D51")))
            }
        }.start()
    }

    @SuppressLint("ServiceCast")
    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etName.windowToken, 0)
    }
}