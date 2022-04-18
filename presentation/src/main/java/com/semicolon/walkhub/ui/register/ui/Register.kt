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
import android.widget.TextView
import androidx.activity.viewModels
import com.semicolon.domain.enums.SexType
import com.semicolon.domain.param.user.CheckPhoneNumberParam
import com.semicolon.domain.param.user.PostUserSignUpParam
import com.semicolon.domain.param.user.VerifyPhoneNumberSignUpParam
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.MainActivity
import com.semicolon.walkhub.ui.register.SearchSchoolActivity
import com.semicolon.walkhub.viewmodel.login.LoginViewModel
import com.semicolon.walkhub.viewmodel.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class Register : BaseActivity<ActivityRegisterBinding>(
    R.layout.activity_register
) {
    private val vm: RegisterViewModel by viewModels()

    var a: Int? = null
    var id: String = ""
    var phone: String = ""

    private lateinit var postUserSignUpParam: PostUserSignUpParam

    override fun initView() {
        binding.constraint.setOnClickListener {
            hideKeyboard()
        }

        movePage(1)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
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

        is RegisterViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
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
                        vm.setName(name)
                        movePage(2)
                    }
                    else {
                        showShortToast("이름은 (2~10)자 안으로 입력해주세요.")
                    }
                }
            }

            2 -> {
                enterPhone()

                binding.btContinue.setOnClickListener {
                    phone = binding.etName.text.toString()

                    if (phone.length in 11..11) {
                        vm.setPhone(phone)

                        verifyPhone(
                            verifyPhoneNumberSignUpParam = VerifyPhoneNumberSignUpParam(
                                phone
                            )
                        )
                    }
                    else {
                        showShortToast("전화번호를 올바르게 입력해주세요.")
                    }
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
                            vm.setAuthCode(cer)
                            checkPhoneNumber(
                                checkPhoneNumberParam = CheckPhoneNumberParam(
                                    phone,
                                    cer
                                )
                            )
                        }
                    }
                }
            }
            4 -> {
                sendId()

                binding.btContinue.setOnClickListener {
                    val id = binding.etName.text.toString()

                    if (id.length < 4) {
                        showShortToast("5자 이상의 아이디를 입력해주세요.")
                    } else {
                        vm.setUserId(id)
                        checkId()
                    }
                }
            }
            5 -> {
                sendPassWord()

                binding.btContinue.setOnClickListener {
                    val password = binding.etName.text.toString()

                    if (password.length in 1..7) {
                        showShortToast("8자 이상의 비밀번호를 입력해주세요.")
                    } else if (password.length > 7) {
                        vm.setPassword(password)
                        movePage(6)
                    }
                }
            }

            6 -> {
                sendSchool()
                hideKeyboard()

                val c = 0
                binding.etName.setOnClickListener {
                    val intent = Intent(this, SearchSchoolActivity::class.java)
                    startActivity(intent)
                }

                binding.btContinue.setOnClickListener {
                    if(binding.etName.length() > 0){
                        vm.setSchool(c)
                    }
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun verifyPhone(verifyPhoneNumberSignUpParam: VerifyPhoneNumberSignUpParam) {
        id = binding.etName.text.toString()
        verifyPhoneNumberSignUpParam.phone_number = binding.etName.text.toString()
        vm.verifyPhone(verifyPhoneNumberSignUpParam)
    }

    private fun checkPhoneNumber(checkPhoneNumberParam: CheckPhoneNumberParam){
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
    }

    private fun enterPhone() {
        setTextWatcher(2)

        binding.tvMain2.visibility = View.GONE
        binding.tvWarning.visibility = View.GONE
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
        binding.etName.hint = "비밀번호 (8~30자, 특수문자 1개 이상)"
        binding.tvEt.text = "비밀번호는 (8~30)자 및 특수문자 1개 이상 포함"
        binding.etName.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
    }

    private fun sendSchool() {
        binding.tvMain.text = "학교 등록"
        binding.tvEt.text = "현재 소속 중인 학교를 입력해주세요."
        binding.etName.hint = "학교 검색하기"
    }

    @SuppressLint("ServiceCast")
    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etName.windowToken, 0)
    }
}