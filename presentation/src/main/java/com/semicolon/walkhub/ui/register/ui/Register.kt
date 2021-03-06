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
import androidx.activity.viewModels
import com.semicolon.domain.param.user.CheckPhoneNumberParam
import com.semicolon.domain.param.user.VerifyPhoneNumberSignUpParam
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.register.SearchSchoolActivity
import com.semicolon.walkhub.util.invisible
import com.semicolon.walkhub.viewmodel.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
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
            binding.btContinue.background = ContextCompat.getDrawable(
                applicationContext,
                R.drawable.register_btn
            )
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
            TODO("?????? ?????? ??????")
        }

        is RegisterViewModel.Event.ErrorMessage -> showShortToast(event.message)

        is RegisterViewModel.Event.BadRequest -> showShortToast("?????????????????? ??????????????? ????????????????????????.")

        is RegisterViewModel.Event.UnAuthor -> showShortToast("?????? ????????? ?????????????????????.")

        is RegisterViewModel.Event.Conflict -> showShortToast("?????? ??????????????? ????????? ?????????????????????.")

        is RegisterViewModel.Event.UnKnown -> showShortToast("??? ??? ?????? ????????? ?????????????????????.")

        is RegisterViewModel.Event.NotFound -> showShortToast("????????? ???????????????.")
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
                            binding.tvWarning.text = "????????? ?????? 2??? ??????????????? ?????????."
                        }

                        if (p0.length > 10) {
                            binding.btContinue.background = ContextCompat.getDrawable(
                                applicationContext, R.drawable.registerbuttondesign
                            )

                            binding.tvWarning.visible()
                            binding.tvWarning.text = "????????? ?????? 10??? ???????????? ?????????."
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
                                binding.tvWarning.text = "????????? ???????????? ????????? ??????????????????."
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
                                binding.tvWarning.text = "??????????????? 5???????????????."
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
                        showShortToast("????????? (2~10)??? ????????? ??????????????????.")
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
                        showShortToast("??????????????? ???????????? ??????????????????.")
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
                            showShortToast("????????? ????????? ??????????????? ??????????????????.")
                        }
                        cer.length > 5 -> {
                            showShortToast("??????????????? ???????????? ??????????????????.")
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
                        showShortToast("5??? ????????? ???????????? ??????????????????.")
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
                        showShortToast("8??? ????????? ??????????????? ??????????????????.")
                    } else if (password.length > 7) {
                        val passwordPattern =
                            "^.*(?=^.{8,30}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$"

                        val tt = Pattern.matches(passwordPattern, password)

                        if (tt) {
                            RegisterViewModel.password = password
                            movePage(6)
                        } else {
                            showShortToast("????????????????????? ?????? ?????? ????????? ??????????????? ????????????????????????.")
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
        binding.tvMain.text = "??????????????????"
        binding.tvMain2.visible()
        binding.tvMain2.text = "???????????? ?????? ??????"
        binding.tvEt.text = "?????? ????????? ????????? ??????????????????."
        binding.etName.hint = "??????(2~10???)"
        binding.etName.inputType = InputType.TYPE_CLASS_TEXT
        binding.ibBack.setImageResource(R.drawable.delete)
    }

    private fun enterPhone() {
        setTextWatcher(2)

        binding.tvMain2.visibility = View.GONE
        binding.tvWarning.visibility = View.GONE
        binding.tvMinute.visibility = View.GONE
        binding.tvSecond.visibility = View.GONE
        binding.btReCer.visibility = View.GONE
        binding.tvMain.text = "???????????? ??????"
        binding.tvEt.text = "??????????????? ??????????????????"
        binding.etName.hint = "???????????? ex) 01012345678"
        binding.etName.inputType = InputType.TYPE_CLASS_NUMBER
        binding.ibBack.setImageResource(R.drawable.ic_back_arrow)
    }

    private fun sendCertification() {
        setTextWatcher(3)

        binding.tvEt.visibility = View.GONE
        binding.tvWarning.visibility = View.GONE
        binding.etName.hint = "??????????????????"
        binding.btReCer.visible()
        binding.tvMinute.visible()
        binding.tvSecond.visible()
        binding.devide.visible()
        binding.etName.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

        if (!b) fiveTimer()
    }

    private fun sendId() {
        setTextWatcher(4)

        binding.etName.hint = "????????? (5~30???)"
        binding.tvMain.text = "?????????"
        binding.tvEt.visible()
        binding.tvEt.text = "???????????? (5~30)??? ??????"
        binding.devide.visibility = View.GONE
        binding.btReCer.visibility = View.GONE
        binding.tvMinute.visibility = View.GONE
        binding.tvSecond.visibility = View.GONE
        binding.etName.maxEms
    }

    private fun sendPassWord() {
        setTextWatcher(5)

        binding.tvMain.text = "????????????"
        binding.etName.hint = "???????????? (8~30???)"
        binding.tvEt.text = "??????????????? (8~30)??? ??? ????????? ???????????? \n?????? 1??? ?????? ??????"
        binding.etName.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
    }

    private fun sendSchool() {
        binding.tvMain.text = "?????? ??????"
        binding.tvEt.text = "?????? ?????? ?????? ????????? ??????????????????."
        binding.etName.hint = "?????? ????????????"
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