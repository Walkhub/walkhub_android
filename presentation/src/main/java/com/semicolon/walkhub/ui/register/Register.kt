package com.semicolon.walkhub.ui.register

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.databinding.DataBindingUtil
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityRegisterBinding
import com.semicolon.walkhub.util.visible

class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        binding.constraint.setOnClickListener {
            hideKeyboard()
        }

        movePage(1)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
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
                                binding.tvWarning.setTextColor(Color.parseColor("#4D99F0"))
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

                    6 -> {

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

                    if (name.length in 2..10) nextPage(2)
                    else {
                        Toast.makeText(this, "이름은 (2~10)자안으로 입력해야합니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            2 -> {
                enterPhone()

                binding.btContinue.setOnClickListener {
                    val phone = binding.etName.text.toString()

                    if (phone.length in 11..11) nextPage(3)
                    else {
                        Toast.makeText(this, "전화번호를 올바르게 입력해주세요", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            3 -> {
                sendCertification()

                binding.btContinue.setOnClickListener {
                    val cer = binding.etName.text.toString()

                    if (cer.length < 5) {
                        Toast.makeText(this, "올바른 형식의 인증번호를 입력해주세요", Toast.LENGTH_SHORT).show()
                    } else nextPage(4)
                }
            }
            4 -> {
                sendId()

                binding.btContinue.setOnClickListener {
                    val id = binding.etName.text.toString()

                    if (id.length < 4) {
                        Toast.makeText(this, "최소 5자 이상의 아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
                    } else nextPage(5)
                }
            }
            5 -> {
                sendPassWord()

                binding.btContinue.setOnClickListener {
                    val password = binding.etName.text.toString()

                    if (password.length in 1..7) {
                        Toast.makeText(
                            this,
                            "비밀번호는 8~30자 내로 특수문자를 포함하여 입력해주세요.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else if (password.length > 7) {
                        movePage(6)
                    }
                }
            }

            6 -> {
                sendSchool()

                binding.etName.setOnClickListener {

                }
            }
        }
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
        binding.btReCer.visible()
        binding.tvMinute.visible()
        binding.tvSecond.visible()
        binding.etName.hint = "인증번호입력"
    }

    private fun sendId() {
        setTextWatcher(4)

        binding.etName.hint = "아이디 (5~30자)"
        binding.tvMain.text = "아이디"
        binding.tvEt.visible()
        binding.tvEt.text = "아이디는 (5~30)자 포함"
        binding.btReCer.visibility = View.GONE
        binding.tvMinute.visibility = View.GONE
        binding.tvSecond.visibility = View.GONE
        binding.etName.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
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
        setTextWatcher(6)

        binding.tvMain.text = "학교 등록"
        binding.tvEt.text = "현재 소속 중인 학교를 입력해주세요."
        binding.etName.hint = "학교 검색하기"
    }

    private fun nextPage(page: Int) {
        movePage(page)
    }

    @SuppressLint("ServiceCast")
    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etName.windowToken, 0)
    }
}